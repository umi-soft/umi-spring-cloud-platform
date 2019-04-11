package cn.umisoft.gateway.security.authentication.jwt;

import cn.umisoft.gateway.remote.SystemAdminAPI;
import cn.umisoft.util.enums.RedisKeyEnum;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    @Lazy
    private StringRedisTemplate redisTemplate;
    @Autowired
    @Lazy
    private SystemAdminAPI systemAdminAPI;

    @Override
    public Mono<UserDetails> findByUsername(String userId) {
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(getUserPermissions(userId)).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return Mono.just(new JwtUserDetails(userId, authorities));
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
