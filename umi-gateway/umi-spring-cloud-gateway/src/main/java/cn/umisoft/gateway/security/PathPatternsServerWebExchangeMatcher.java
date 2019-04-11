package cn.umisoft.gateway.security;

import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.*;


public class PathPatternsServerWebExchangeMatcher implements ServerWebExchangeMatcher {
    private static final PathPatternParser DEFAULT_PATTERN_PARSER = new PathPatternParser();
    private final PathPattern pattern;

    public PathPatternsServerWebExchangeMatcher(String pattern) {
        Assert.notNull(pattern, "pattern cannot be null");
        this.pattern = DEFAULT_PATTERN_PARSER.parse(pattern);
    }

    @Override
    public Mono<MatchResult> matches(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        PathContainer path = request.getPath().pathWithinApplication();
        if ("".equals(request.getPath().contextPath().value())) {
            String contextPath = "/" + path.value().split("/")[1];
            path = request.getPath().modifyContextPath(contextPath).pathWithinApplication();
        }
        boolean match = this.pattern.matches(path);
        if (!match) {
            return MatchResult.notMatch();
        } else {
            return MatchResult.match();
        }
    }

    @Override
    public String toString() {
        return "PathPatternsServerWebExchangeMatcher{pattern='" + this.pattern + '\'' +  '}';
    }

    public static PathPatternsServerWebExchangeMatcher[] creator(String...patterns) {
        List<PathPatternsServerWebExchangeMatcher> list = new ArrayList<PathPatternsServerWebExchangeMatcher>();
        Arrays.asList(patterns).forEach(pattern -> list.add(new PathPatternsServerWebExchangeMatcher(pattern)));
        return list.toArray(new PathPatternsServerWebExchangeMatcher[list.size()]);
    }
}
