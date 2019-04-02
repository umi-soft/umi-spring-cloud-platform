package cn.umisoft.gateway.filter;

import cn.umisoft.gateway.feign.SystemAdminService;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import cn.umisoft.util.context.UmiUserContextHolder;
import cn.umisoft.util.jwt.JwtProperties;
import cn.umisoft.util.jwt.JwtUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.*;


@Configuration
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    final static String REDIS_USER_ROLE_KEY_PREFIX = "USER_ROLE_";
    final static String REDIS_PLATFORM_PERMISION_KEY = "REDIS_PLATFORM_PERMISION_KEY";


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    @Lazy
    private SystemAdminService systemAdminService;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        LinkedHashSet requiredAttribute = serverWebExchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        ServerHttpRequest request = serverWebExchange.getRequest();
        String requestUri = request.getPath().pathWithinApplication().value();
        PathMatcher matcher = new AntPathMatcher();

        // 不进行拦截的地址
        for (String excludePathPattern : jwtProperties.getExcludePathPatterns()) {
            if (matcher.match(excludePathPattern, requestUri)) {
                log.info("规则【" + excludePathPattern + "】匹配了当前请求【" + requestUri + "】终止继续拦截");
                ServerHttpRequest build = request.mutate().build();
                return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
            }
        }

        String userId = null;
        try {
            log.info("网关层开始验证token，验证完成后，将token转化为服务层可用的token信息");
            String token = getToken(request);
            userId = JwtUtils.verify(token, jwtProperties.getGatewaySecret(), JwtUtils.GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX, redisTemplate);
            Map<String, String> context = new HashMap<String, String>();
            context.put(UmiUserContextHolder.USER_ID_KEY, userId);
            log.info("正在更新本地token(替换秘钥重新加密)，更新前：" + token);
            token = JwtUtils.changeTokenSecret(token, jwtProperties.getGatewaySecret(), jwtProperties.getServiceSecret());
            context.put(UmiUserContextHolder.JWT_KEY, token);
            log.info("正在更新本地token(替换秘钥重新加密)，更新后：" + token);
            UmiUserContextHolder.setContext(context);
        } catch (TokenExpiredException e) {
            log.error("token过期", e);
            return getVoidMono(serverWebExchange, ApiResultWrapper.sessionExpired());
        } catch (JWTVerificationException e) {
            log.error("非法的token信息", e);
            return getVoidMono(serverWebExchange, ApiResultWrapper.sessionIllegal());
        }
        JSONArray roles = getUserRoles(userId);
        JSONObject permissions = getPlatformPermissions();

        log.info("进行资源访问权限判断");
        // TODO 进行资源访问权限判断

        log.info("权限检查通过，防止没有网络隔离情况下，服务被直接代理访问，重新设置请求中token信息，设置为服务层token： " +  UmiUserContextHolder.getJwtToken());
        ServerHttpRequest token = serverWebExchange.getRequest().mutate().headers((httpHeaders) -> {
            httpHeaders.remove(JwtUtils.HEAD_TOKEN_KEY);
            httpHeaders.add(JwtUtils.HEAD_TOKEN_KEY, UmiUserContextHolder.getJwtToken());
        }).build();

        getToken(token);
        return gatewayFilterChain.filter(serverWebExchange.mutate().request(token).build());
    }

    private String getToken(ServerHttpRequest request) {
        log.info("开始尝试从请求header中获取token");
        List<String> tokens = request.getHeaders().get(JwtUtils.HEAD_TOKEN_KEY);
        String token = null;
        if( tokens == null || tokens.size() == 0 ) {
            log.info("开始尝试从请求参数中获取token");
            tokens = request.getQueryParams().get(JwtUtils.PARAMETER_TOKEN_KEY);
        }
        if (tokens != null && tokens.size() > 0) {
            token =  tokens.get(0);
        }
        log.info("获取到token值为： " + token);
        return token;
    }

    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, ApiResult body) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    private JSONArray getUserRoles(String userId) {
        JSONArray roles = new JSONArray();
        log.info("读取Redis中的用户权限信息");
        String value = redisTemplate.opsForValue().get(REDIS_USER_ROLE_KEY_PREFIX + userId);
        if (value == null || "".equals(value.trim())) {
            log.info("调用服务，重新初始化redis中用户权限信息");
            // TODO 调用服务，重新初始化redis中用户权限信息
            value = redisTemplate.opsForValue().get(REDIS_USER_ROLE_KEY_PREFIX + userId);
            log.info("正在重新读取Redis中的用户权限信息");
        }
        if (value != null && !"".equals(value.trim())) {
            roles.addAll(JSONArray.parseArray(value));
        }
        return roles;
    }

    private JSONObject getPlatformPermissions() {
        JSONObject permissions = new JSONObject();
        log.info("读取Redis中的服务权限信息");
        String value = redisTemplate.opsForValue().get(REDIS_PLATFORM_PERMISION_KEY);
        if (value == null || "".equals(value.trim())) {
            log.info("调用服务，重新初始化redis中服务权限信息");
            // TODO 调用服务，重新初始化redis中服务权限信息
            log.info("正在重新读取Redis中的服务权限信息");
            value = redisTemplate.opsForValue().get(REDIS_PLATFORM_PERMISION_KEY);
        }
        if (value != null && !"".equals(value.trim())) {
            permissions = JSONObject.parseObject(value);
        }
        return permissions;
    }
}
