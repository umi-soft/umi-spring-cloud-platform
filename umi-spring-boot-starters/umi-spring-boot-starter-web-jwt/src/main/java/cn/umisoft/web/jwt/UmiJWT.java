package cn.umisoft.web.jwt;

import cn.umisoft.util.context.UmiUserContextHolder;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
public class UmiJWT {

    /**
     * @description: <p>统一的从request获取token方法</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/22 8:13 AM
     */
    public static String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            authorization = (String)request.getParameter("token");
        }
        return authorization;
    }

    /**
     * @description: <p>JWT生成器，采用HMAC256算法</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/18 8:42 PM
     * @param user 用户信息
     * @param secret 秘钥
     * @param amount token有效期（分钟）
     * @param redisTemplate 可用的redis连接
     * @return
     */
    public static String create(JSONObject user, String secret, Integer amount, StringRedisTemplate redisTemplate) {
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
        redisTemplate.opsForValue().set(keyId, jwt, amount, TimeUnit.MINUTES);
        return jwt;
    }

    /**
     * @description: <p>双重验证token，并返回用户ID</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/19 3:01 PM
     * @param userToken 用户提交的jwt token
     * @param secret 秘钥
     * @param redisTemplate 可用的redis连接
     * @return 用户ID
     */
    public static String verify(String userToken, String secret, StringRedisTemplate redisTemplate) {
        if (userToken == null) {
            throw new JWTVerificationException("缺失Token信息");
        }
        userToken = userToken.replaceFirst("Bearer ","");
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT jwt = verifier.verify(userToken);
        // redis层验证
        String redisToken = redisTemplate.opsForValue().get(jwt.getKeyId());
        if (redisToken == null) {
            throw new TokenExpiredException("回话过期");
        }
        jwt = verifier.verify(redisToken);
        return jwt.getKeyId();
    }
    /**
     * @description: <p>退出用户登录</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/19 3:01 PM
     * @param secret 秘钥
     * @param redisTemplate 可用的redis连接
     * @return 用户ID
     */
    public static void logout(String secret, StringRedisTemplate redisTemplate) {
        String redisKey = UmiUserContextHolder.getContext();
        if (redisKey != null) {
            redisTemplate.delete(redisKey);
        }
        UmiUserContextHolder.setContext(null);
    }
}
