package cn.umisoft.web.interceptor;

import cn.umisoft.util.exceptions.ControllerFieldCheckException;
import cn.umisoft.util.jwt.JwtUtils;
import cn.umisoft.util.context.UmiUserContextHolder;
import cn.umisoft.util.jwt.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    protected JwtProperties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("JWTInterceptor.preHandle 正在处理 + " + request.getRequestURI());
        String authorization = JwtUtils.getToken(request);
        if (authorization == null) {
            throw new ControllerFieldCheckException( request.getRequestURI() + "缺失token信息");
        }
        // 验证JWT，并为线程绑定当前登录人信息ID
        Map<String, String> context = new HashMap<String, String>();
        context.put(UmiUserContextHolder.USER_ID_KEY, JwtUtils.verify(authorization, properties.getServiceSecret()));
        context.put(UmiUserContextHolder.JWT_KEY, authorization);
        UmiUserContextHolder.setContext(context);
        log.info("JWTInterceptor.preHandle 完成UmiUserContextHolder用户信息线程绑定"  + request.getRequestURI());
        return true;
    }
}
