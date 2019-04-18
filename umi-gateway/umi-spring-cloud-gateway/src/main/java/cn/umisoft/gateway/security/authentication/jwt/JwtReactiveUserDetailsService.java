package cn.umisoft.gateway.security.authentication.jwt;

import cn.umisoft.feign.remote.SystemAdminAPI;
import cn.umisoft.util.context.UmiUserContextHolder;
import cn.umisoft.util.enums.RedisKeyEnum;
import cn.umisoft.util.jwt.JwtProperties;
import cn.umisoft.util.jwt.JwtUtils;
import com.alibaba.fastjson.JSONArray;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class JwtReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    @Lazy
    private JwtProperties jwtProperties;
    @Autowired
    @Lazy
    private StringRedisTemplate redisTemplate;
    @Autowired
    @Lazy
    private SystemAdminAPI systemAdminAPI;
    /**
     * @description: <p>验证和解析token，以及加载UserDetails</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/16 11:42 PM
     * @param: String token
     */
    @Override
    public Mono<UserDetails> findByUsername(String token) {
        String userId = validateToken(token);
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(getUserPermissions(userId)).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return Mono.just(new JwtUserDetails(userId, authorities));
    }

    /**
     * @description: <p>验证token</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/9 9:59 PM
     */
    public String validateToken(String token) {
        try {
            String userId = JwtUtils.verify(token, jwtProperties.getGatewaySecret(), JwtUtils.GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX, redisTemplate);
            Map<String, String> context = new HashMap<String, String>();
            context.put(UmiUserContextHolder.USER_ID_KEY, userId);
            log.info("正在更新本地token(替换秘钥重新加密)，更新前：" + token);
            token = JwtUtils.changeTokenSecret(token, jwtProperties.getGatewaySecret(), jwtProperties.getServiceSecret());
            context.put(UmiUserContextHolder.JWT_KEY, token);
            log.info("正在更新本地token(替换秘钥重新加密)，更新后：" + token);
            UmiUserContextHolder.setContext(context);
            return userId;
        } catch (TokenExpiredException e) {
            log.error("token过期", e);
            throw new NonceExpiredException("token过期", e);
        } catch (JWTVerificationException e) {
            log.error("非法的token信息", e);
            throw new BadCredentialsException("非法的token信息", e);
        }
    }

    private String[] getUserPermissions(String userId) {
        List<String>  roles = new ArrayList<String>();
        log.info("读取Redis中的用户权限信息");
        String key = RedisKeyEnum.USER_PERMISSIONS_KEY_PREFIX.toString() + userId;
        String value = redisTemplate.opsForValue().get(key);
        if (value == null || "".equals(value.trim())) {
            log.info("调用服务，重新初始化redis中用户权限信息");
            systemAdminAPI.getUserPermissions();
            value = redisTemplate.opsForValue().get(key);
            log.info("正在重新读取Redis中的用户权限信息");
        }
        if (value != null && !"".equals(value.trim())) {
            roles = JSONArray.parseArray(value).toJavaList(String.class);
        }
        log.info("用户没有权限信息");
        return roles.toArray(new String[roles.size()]);
    }
}
