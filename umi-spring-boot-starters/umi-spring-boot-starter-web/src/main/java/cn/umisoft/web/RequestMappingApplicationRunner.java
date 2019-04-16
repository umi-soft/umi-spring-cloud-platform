package cn.umisoft.web;

import cn.umisoft.feign.remote.SystemAdminAPI;
import cn.umisoft.util.context.UmiUserContextHolder;
import cn.umisoft.util.enums.Boolean;
import cn.umisoft.util.jwt.JwtProperties;
import cn.umisoft.util.jwt.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: <p>服务启动完成后强行更新服务所有的controller信息</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/4/12 11:24 AM
 */
public class RequestMappingApplicationRunner implements ApplicationRunner {

    @Value("${spring.application.name}")
    private String serviceName;
    @Value("${umisoft.init-micro-service-security}")
    private boolean initMicroServiceSecurity;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    @Autowired
    @Lazy
    private SystemAdminAPI systemAdminAPI;
    @Autowired
    protected JwtProperties properties;
    @Autowired
    protected StringRedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!initMicroServiceSecurity) { return; }
        Iterator<?> iterator = this.handlerMapping.getHandlerMethods().entrySet().iterator();
        Map<String, Map<String, String>> authoritieMap = new HashMap<String, Map<String, String>>();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            RequestMappingInfo mappingInfo = (RequestMappingInfo)entry.getKey();
            mappingInfo.getPatternsCondition().getPatterns().stream().map(p -> {
                Map<String, String> map = new HashMap<String, String>();
                String id = DigestUtils.md5DigestAsHex(("/" + serviceName + p).getBytes());
                map.put("id", id);
                map.put("serviceId", serviceName);
                map.put("securityDef", p);
                map.put("fromSystem", Boolean.TRUE.getKey());
                map.put("name", mappingInfo.getName());
                map.put("remark", mappingInfo.toString());
                authoritieMap.put(id, map);
                return map;
            }).collect(Collectors.toList());
        }
        // 服务启动并没有回话，临时设置用户信息上下文
        Map<String, String> map = new HashMap<String, String>();
        JSONObject user = new JSONObject();
        user.put("id", "admin");
        user.put("name", "admin");
        map.put(UmiUserContextHolder.USER_ID_KEY, user.getString("id"));
        map.put(UmiUserContextHolder.JWT_KEY, JwtUtils.create(user, properties.getServiceSecret(), properties.getMinutes(), JwtUtils.SERVICE_REDIS_USER_TOKEN_KEY_PREFIX, redisTemplate));
        UmiUserContextHolder.setContext(map);
        systemAdminAPI.initMicroServiceSecurity(authoritieMap.values().stream().collect(Collectors.toList()));
        JwtUtils.logout(redisTemplate);
    }
}
