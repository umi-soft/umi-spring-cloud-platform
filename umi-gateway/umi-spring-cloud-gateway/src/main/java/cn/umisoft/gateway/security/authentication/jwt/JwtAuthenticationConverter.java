package cn.umisoft.gateway.security.authentication.jwt;

import cn.umisoft.util.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
public class JwtAuthenticationConverter implements ServerAuthenticationConverter {

    private final JwtAuthenticationTokenProvider authenticationTokenProvider;

    public JwtAuthenticationConverter(JwtAuthenticationTokenProvider authenticationTokenProvider) {
        this.authenticationTokenProvider = authenticationTokenProvider;
    }

    private Mono<String> resolveToken(ServerWebExchange exchange) {
        log.debug("exchange request path: {}", exchange.getRequest().getPath());
        String token = getToken(exchange.getRequest());
        if (token != null && token.startsWith(JwtUtils.JWT_PREFIX)) {
            return Mono.just(token)
                    .filter(t -> t.startsWith(JwtUtils.JWT_PREFIX))
                    .map(t -> t.substring(JwtUtils.JWT_PREFIX.length()));
        }
        return Mono.justOrEmpty(token);
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return resolveToken(exchange)
                .map(authenticationTokenProvider::getAuthentication);
    }

    private String getToken(ServerHttpRequest request) {
        log.info("开始尝试从请求header中获取token");
        String token = request.getHeaders().getFirst(JwtUtils.HEAD_TOKEN_KEY);
        if( token == null || "".equals(token.trim())) {
            log.info("header中未获取成功，开始尝试从请求参数中获取token");
            token = request.getQueryParams().getFirst(JwtUtils.PARAMETER_TOKEN_KEY);
        }
        log.info("获取到token值为： " + token);
        return token;
    }
}