package cn.umisoft.gateway.security.authentication.jwt;

import java.util.*;

import cn.umisoft.util.context.UmiUserContextHolder;
import cn.umisoft.util.jwt.JwtProperties;
import cn.umisoft.util.jwt.JwtUtils;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;

@Slf4j
public class JwtAuthenticationTokenProvider {

    @Autowired
    @Lazy
    private JwtProperties jwtProperties;
    @Autowired
    @Lazy
    private StringRedisTemplate redisTemplate;


    public Authentication getAuthentication(String token) {
        return new JwtAuthenticationToken(validateToken(token));
    }

    /**
     * @description: <p>验证token</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/9 9:59 PM
     */
    public String validateToken(String token) {
        try {
            String userId = JwtUtils.verify(token, jwtProperties.getGatewaySecret(), JwtUtils.GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX, redisTemplate);
            Map<String, String> context = new HashMap<String, String>();
            context.put(UmiUserContextHolder.USER_ID_KEY, userId);
            log.info("正在更新本地token(替换秘钥重新加密)，更新前：" + token);
            token = JwtUtils.changeTokenSecret(token, jwtProperties.getGatewaySecret(), jwtProperties.getServiceSecret());
            context.put(UmiUserContextHolder.JWT_KEY, token);
            log.info("正在更新本地token(替换秘钥重新加密)，更新后：" + token);
            UmiUserContextHolder.setContext(context);
            return userId;
        } catch (TokenExpiredException e) {
            log.error("token过期", e);
        } catch (JWTVerificationException e) {
            log.error("非法的token信息", e);
        }
        return null;
    }
}
