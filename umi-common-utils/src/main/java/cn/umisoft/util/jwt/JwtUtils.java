package cn.umisoft.util.jwt;

import cn.umisoft.util.context.UmiUserContextHolder;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description: JWT工具类
 * @Author: hujie@umisoft.cn
 * @Date: 2019/2/18 12:42 PM
 */
@Slf4j
public class JwtUtils {
    public final static String JWT_PREFIX = "Bearer ";
    public final static String HEAD_TOKEN_KEY = "Authorization";
    public final static String PARAMETER_TOKEN_KEY = "token";
    public final static String SERVICE_REDIS_USER_TOKEN_KEY_PREFIX = "SERVICE_USER_TOKEN_";
    public final static String GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX = "GATEWAY_USER_TOKEN_";

    /**
     * @description: <p>统一的从request获取token方法</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/22 8:13 AM
     */
    public static String getToken(HttpServletRequest request) {
        log.info("正在尝试从请求header中获取token");
        String authorization = request.getHeader(HEAD_TOKEN_KEY);
        if (authorization == null) {
            log.info("正在尝试从请求参数中获取token");
            authorization = (String)request.getParameter(PARAMETER_TOKEN_KEY);
        }
        log.info("获取到token信息为： " + authorization);
        return authorization;
    }



    /**
     * @description: <p>JWT生成器，采用HMAC256算法</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/18 8:42 PM
     * @param user 用户信息
     * @param secret 秘钥
     * @param amount token有效期（分钟）
     * @param redisKeyPrefix redis token key前缀
     * @param redisTemplate 可用的redis连接
     * @return
     */
    public static String create(JSONObject user, String secret, Integer amount, String redisKeyPrefix, StringRedisTemplate redisTemplate) {
        JSONObject jwtSubject = new JSONObject();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MINUTE, amount);
        String keyId = (String)user.get("id");
        String name = (String)user.get("name");
        String jwt = JWT.create()
                .withJWTId(keyId)        // 编号, 保持与用户ID相同
                .withKeyId(keyId)        // Redis KEY使用，用户ID开头，Redis过期时间为最终有效的时间
                .withIssuedAt(now)              // 签发时间
                .withIssuer(name)     // 签发人
                .withExpiresAt(cal.getTime())   // 过期时间
                .withNotBefore(now)
                .withSubject(jwtSubject.toJSONString())
                .sign(Algorithm.HMAC256(secret));
        redisTemplate.opsForValue().set(getRedisTokenKey(redisKeyPrefix, keyId), jwt, amount, TimeUnit.MINUTES);
        log.info("生成的token为： " + jwt);
        return jwt;
    }

    /**
     * @description: <p>双重验证token，并返回用户ID</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/19 3:01 PM
     * @param token 用户提交的jwt token
     * @param secret 秘钥
     * @param redisKeyPrefix redis token key前缀
     * @param redisTemplate 可用的redis连接
     * @return 用户ID
     */
    public static String verify(String token, String secret, String redisKeyPrefix, StringRedisTemplate redisTemplate) {
        // redis层验证
        String redisToken = redisTemplate.opsForValue().get(getRedisTokenKey(redisKeyPrefix, verify(token, secret)));
        if (redisToken == null) {
            log.info("redis中token为null");
            throw new TokenExpiredException("回话过期");
        }
        return verify(redisToken, secret);
    }
    /**
     * @description: <p>验证token，并返回用户ID</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/19 3:01 PM
     * @param token 用户提交的jwt token
     * @param secret 秘钥
     * @return 用户ID
     */
    public static String verify(String token, String secret) {
        log.info("被验证的token为： " + token);
        if (token == null) {
            throw new JWTVerificationException("缺失Token信息");
        }
        token = token.replaceFirst(JWT_PREFIX,"");
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        return verifier.verify(token).getKeyId();
    }

    public static String changeTokenSecret(String token, String oldSecret, String newSecret) {
        if (token == null) {
            log.info("重新加密的token信息为null");
            return null;
        }
        token = token.replaceFirst(JWT_PREFIX,"");
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(oldSecret)).build();
        DecodedJWT jwt = verifier.verify(token);
        return JWT_PREFIX + JWT.create()
                .withJWTId(jwt.getId())        // 编号, 保持与用户ID相同
                .withKeyId(jwt.getKeyId())        // Redis KEY使用，用户ID开头，Redis过期时间为最终有效的时间
                .withIssuedAt(jwt.getIssuedAt())              // 签发时间
                .withIssuer(jwt.getIssuer())     // 签发人
                .withExpiresAt(jwt.getExpiresAt())   // 过期时间
                .withNotBefore(jwt.getNotBefore())
                .withSubject(jwt.getSubject())
                .sign(Algorithm.HMAC256(newSecret));
    }
    /**
     * @description: <p>退出用户登录</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/19 3:01 PM
     * @param redisTemplate 可用的redis连接
     * @return 用户ID
     */
    public static void logout(StringRedisTemplate redisTemplate) {
        String userId = UmiUserContextHolder.getContext();
        if (userId != null && !"".equals(userId.trim())) {
            redisTemplate.delete(getRedisTokenKey(SERVICE_REDIS_USER_TOKEN_KEY_PREFIX, userId));
            redisTemplate.delete(getRedisTokenKey(GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX, userId));
            log.info("redis中token已销毁");
        }
        UmiUserContextHolder.setContext(null);
    }

    public static String getRedisTokenKey(String prefix, String suffix) {
        return prefix + suffix;
    }
}
