package cn.umisoft.web;

import cn.umisoft.web.interceptor.JWTInterceptor;
import cn.umisoft.web.properties.UmiJwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: <p> jwt 拦截请求，并解析token，设置线程用户上下文</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/3/26 10:22 PM
 */
@EnableConfigurationProperties(UmiJwtProperties.class)
@Configuration
public class JwtInterceptorAutoConfig implements WebMvcConfigurer {

    @Autowired
    protected UmiJwtProperties properties;

    /**
     * @description: <p>JWT拦截器，便于组件注入</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/19 10:29 PM
     */
    @Bean
    public JWTInterceptor jwtInterceptor() {
        return new JWTInterceptor();
    }

    /**
     * @description: <p>拦截器注册</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/15 9:07 AM
     * @param: registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(jwtInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(properties.getExcludePathPatterns());
    }
}
