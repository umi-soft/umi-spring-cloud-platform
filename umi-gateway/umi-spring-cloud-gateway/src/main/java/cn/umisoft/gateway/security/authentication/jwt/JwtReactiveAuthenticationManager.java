package cn.umisoft.gateway.security.authentication.jwt;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final ReactiveUserDetailsService userDetailsService;

    public JwtReactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService){
        Assert.notNull(userDetailsService, "userDetailsService cannot be null");
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return this.userDetailsService.findByUsername((String)authentication.getCredentials()).filter((u) -> {
            return u.isEnabled();
        }).switchIfEmpty(Mono.defer(() -> {
            return Mono.error(new BadCredentialsException("非法的token信息"));
        })).map((u) -> {
            // 将认证使用的token记录在credentials中
            return new JwtAuthenticationToken(u.getUsername(), authentication.getCredentials(), u.getAuthorities());
        });
    }
}
