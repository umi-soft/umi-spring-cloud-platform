package cn.umisoft.gateway.security.authorization;

import cn.umisoft.gateway.security.RedisMemorySecurityMetadataSource;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.util.Set;

public class AuthorityReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private RedisMemorySecurityMetadataSource securityMetadataSource;

    public AuthorityReactiveAuthorizationManager(RedisMemorySecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext context) {
        String key = context.getExchange().getRequest().getPath().value();
        Set<String> permissions = securityMetadataSource.getSecuritySet(key);

        return authentication.filter((a) -> {
            return a.isAuthenticated();
        }).flatMapIterable((a) -> {
            return a.getAuthorities();
        }).map((g) -> {
            return g.getAuthority();
        }).takeUntil((p) -> {
            return permissions.contains(p);
        }).hasElements().map((hasAuthority) -> {
            return new AuthorizationDecision(hasAuthority);
        });
    }
}
