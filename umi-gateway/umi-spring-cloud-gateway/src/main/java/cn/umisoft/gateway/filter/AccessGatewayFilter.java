package cn.umisoft.gateway.filter;

import cn.umisoft.util.context.UmiUserContextHolder;
import cn.umisoft.util.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        log.info("防止没有网络隔离情况下，服务被直接代理访问，重新设置请求中token信息，设置为服务隔离层token： " +  UmiUserContextHolder.getJwtToken());
        ServerHttpRequest token = serverWebExchange.getRequest().mutate().headers((httpHeaders) -> {
            httpHeaders.remove(JwtUtils.HEAD_TOKEN_KEY);
            httpHeaders.add(JwtUtils.HEAD_TOKEN_KEY, UmiUserContextHolder.getJwtToken());
        }).build();
        return gatewayFilterChain.filter(serverWebExchange.mutate().request(token).build());
    }

//    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, ApiResult body) {
//        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
//        byte[] bytes = JSONObject.toJSONString(body, SerializerFeature.WriteEnumUsingToString).getBytes(StandardCharsets.UTF_8);
//        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
//        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
//    }
}
