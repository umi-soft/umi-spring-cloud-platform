package cn.umisoft.gateway.security.authentication.jwt;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final ReactiveUserDetailsService userDetailsService;
    private Scheduler scheduler = Schedulers.parallel();

    public JwtReactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService){
        Assert.notNull(userDetailsService, "userDetailsService cannot be null");
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return this.userDetailsService.findByUsername((String)authentication.getPrincipal()).filter((u) -> {
            return u.isEnabled();
        }).switchIfEmpty(Mono.defer(() -> {
            return Mono.error(new BadCredentialsException("Invalid Credentials"));
        })).map((u) -> {
            Authentication a = new JwtAuthenticationToken(u.getUsername(), u.getAuthorities());
            return a;
        });
    }
}
