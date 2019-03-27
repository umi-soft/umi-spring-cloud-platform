package cn.umisoft.web.interceptor;

import cn.umisoft.util.exceptions.ControllerFieldCheckException;
import cn.umisoft.web.jwt.UmiJWT;
import cn.umisoft.util.context.UmiUserContextHolder;
import cn.umisoft.web.properties.UmiJwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: <p>JWT 拦截器，双重验证</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/2/19 10:41 PM
 */
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    protected UmiJwtProperties properties;

    @Autowired
    protected StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = UmiJWT.getToken(request);
        if (authorization == null) {
            throw new ControllerFieldCheckException("缺失token信息");
        }
        // 验证JWT，并为线程绑定当前登录人信息ID
        Map<String, String> context = new HashMap<String, String>();
        context.put(UmiUserContextHolder.USER_ID_KEY, UmiJWT.verify(authorization, properties.getSecret(), redisTemplate));
        context.put(UmiUserContextHolder.JWT_KEY, authorization);
        UmiUserContextHolder.setContext(context);
        return true;
    }
}
