package cn.umisoft.gateway.config;

import cn.umisoft.gateway.security.*;
import cn.umisoft.gateway.security.authentication.HttpBasicServerAuthenticationEntryPoint;
import cn.umisoft.gateway.security.authentication.ServerAuthenticationEntryPointFailureHandler;
import cn.umisoft.gateway.security.authentication.jwt.JwtAuthenticationConverter;
import cn.umisoft.gateway.security.authentication.jwt.JwtReactiveAuthenticationManager;
import cn.umisoft.gateway.security.authentication.jwt.JwtReactiveUserDetailsService;
import cn.umisoft.gateway.security.authorization.AuthorityReactiveAuthorizationManager;
import cn.umisoft.gateway.security.authorization.HttpBasicServerAccessDeniedHandler;
import cn.umisoft.util.jwt.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import java.util.Arrays;

@EnableWebFluxSecurity
@Configuration
@Slf4j
public class SecurityWebFluxConfigration {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * @description: <p>spring security webflux 配置入口</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/10 7:41 PM
     */
    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {

        log.info("正在初始化 ServerHttpSecurity...");
        http.httpBasic().disable()
            .csrf().disable()  //CRSF禁用
            .formLogin().disable() //禁用form登录
            .headers()
                .frameOptions().disable()
                .cache().disable()
            .and()
                .cors()  //支持跨域
            .and()
                .authorizeExchange()
                .matchers(PathPatternsServerWebExchangeMatcher.creator(jwtProperties.getExcludePathPatterns())).permitAll()
                .pathMatchers("/**").access(authorityReactiveAuthorizationManager())
            .and()
                .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .exceptionHandling()
                    .authenticationEntryPoint(new HttpBasicServerAuthenticationEntryPoint())
                    .accessDeniedHandler(new HttpBasicServerAccessDeniedHandler());
        log.info("ServerHttpSecurity 初始化完成");
        return http.build();
    }
    /**
     * @description: <p>认证过滤器</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/10 7:41 PM
     */
    @Bean
    public AuthenticationWebFilter authenticationWebFilter() {
        log.info("正在初始化 AuthenticationWebFilter...");
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(delegatingReactiveAuthenticationManager());
        authenticationWebFilter.setServerAuthenticationConverter(new JwtAuthenticationConverter());
        NegatedServerWebExchangeMatcher negateWhiteList = new NegatedServerWebExchangeMatcher(ServerWebExchangeMatchers.pathMatchers(jwtProperties.getExcludePathPatterns()));
        authenticationWebFilter.setRequiresAuthenticationMatcher(negateWhiteList);
        authenticationWebFilter.setSecurityContextRepository(new WebSessionServerSecurityContextRepository());
        authenticationWebFilter.setAuthenticationFailureHandler(authenticationEntryPointFailureHandler());
        log.info("AuthenticationWebFilter 初始化完成");
        return authenticationWebFilter;
    }
    /**
     * @description: <p>认证管理器</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/10 7:40 PM
     */
    @Bean
    public DelegatingReactiveAuthenticationManager delegatingReactiveAuthenticationManager() {
        log.info("正在初始化 DelegatingReactiveAuthenticationManager...");
        DelegatingReactiveAuthenticationManager authenticationManager = new DelegatingReactiveAuthenticationManager(Arrays.asList(
                new JwtReactiveAuthenticationManager(jwtReactiveUserDetailsService())
        ));
        log.info("DelegatingReactiveAuthenticationManager 初始化完成");
        return authenticationManager;
    }
    /**
     * @description: <p>系统管理服务用户名和密码认证 UserDetails</p>
     * @author: hujie@umisoft.cn
     */
    @Bean
    public JwtReactiveUserDetailsService jwtReactiveUserDetailsService() {
        return new JwtReactiveUserDetailsService();
    }
    @Bean
    public ServerAuthenticationEntryPointFailureHandler authenticationEntryPointFailureHandler() {
        return new ServerAuthenticationEntryPointFailureHandler();
    }
    /**
     * @description: <p>API级别授权管理器</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/10 7:39 PM
     */
    @Bean
    public AuthorityReactiveAuthorizationManager authorityReactiveAuthorizationManager() {
        log.info("正在初始化 AuthorityReactiveAuthorizationManager...");
        AuthorityReactiveAuthorizationManager authorizationManager = new AuthorityReactiveAuthorizationManager(securityMetadataSource());
        log.info("AuthorityReactiveAuthorizationManager 初始化完成");
        return authorizationManager;
    }
    /**
     * @description: <p></p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/11 12:23 AM
     */
    @Bean
    public RedisMemorySecurityMetadataSource securityMetadataSource() {
        return new RedisMemorySecurityMetadataSource();
    }
}
