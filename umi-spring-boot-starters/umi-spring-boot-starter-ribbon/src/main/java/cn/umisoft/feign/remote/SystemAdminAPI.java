package cn.umisoft.feign.remote;

import cn.umisoft.feign.remote.fallback.SystemAdminAPIFallback;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.constatns.MicroServiceConstatns;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = MicroServiceConstatns.SYSTEM_ADMIN, fallbackFactory = SystemAdminAPIFallback.class, configuration = FeignConfiguration.class)
@RequestMapping(value = "/admin")
public interface SystemAdminAPI {

    @GetMapping(value = "auth/user-permissions")
    ApiResult<JSONArray> getUserPermissions();

    @GetMapping(value = "auth/all-platform-permissions")
    ApiResult<JSONObject> getAllPlatformPermissions();

    @PostMapping(value = "security/init-micro-service-security", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ApiResult<JSONObject> initMicroServiceSecurity(@RequestBody List<Map<String, String>> authorities);
}
