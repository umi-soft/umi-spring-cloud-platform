package cn.umisoft.gateway.security;

import cn.umisoft.feign.remote.SystemAdminAPI;
import cn.umisoft.util.enums.RedisKeyEnum;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.SecurityConfig;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class RedisMemorySecurityMetadataSource {
    final static AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    @Lazy
    private SystemAdminAPI systemAdminAPI;

    private Map<String, Set<SecurityConfig>> authorityMap = null;

    public Set<String> getSecuritySet(String url) {
        initResourceMap(false);
        Set<String> roles = Stream.of(this.authorityMap.keySet())
                .flatMap(k -> k.stream())
                .filter(k -> pathMatcher.match(k, url))
                .distinct()
                .flatMap(k -> this.authorityMap.get(k).stream())
                .map(p -> p.getAttribute())
                .distinct()
                .collect(Collectors.toSet());
        // 确保admin角色可以访问所有API
        roles.add("admin");
        return roles;
    }


    public void initResourceMap(boolean force){
        JSONObject permissions = null;
        if (force || authorityMap == null || authorityMap.size() == 0) {
            this.authorityMap = new HashMap<String, Set<SecurityConfig>>();
            permissions = getPlatformPermissions();
        }
        if (permissions != null) {
            Iterator<String> keys = permissions.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                if (!this.authorityMap.containsKey(key)) {
                    this.authorityMap.put(key, new HashSet<SecurityConfig>());
                }
                permissions.getJSONArray(key).forEach(role -> {
                    this.authorityMap.get(key).add(new SecurityConfig((String)role));
                });
            }
        }
    }

    private JSONObject getPlatformPermissions() {
        JSONObject permissions = new JSONObject();
        log.info("读取Redis中的服务权限信息");
        String key = RedisKeyEnum.PLATFORM_PERMISSIONS_KEY.toString();
        String value = redisTemplate.opsForValue().get(key);
        if (value == null || "".equals(value.trim())) {
            log.info("调用服务，重新初始化redis中服务权限信息");
            systemAdminAPI.getAllPlatformPermissions();
            log.info("正在重新读取Redis中的服务权限信息");
            value = redisTemplate.opsForValue().get(key);
        }
        if (value != null && !"".equals(value.trim())) {
            permissions = JSONObject.parseObject(value);
        }
        return permissions;
    }
}
