package cn.umisoft.gateway.remote;

import cn.umisoft.gateway.remote.fallback.SystemAdminAPIFallback;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.constatns.MicroServiceConstatns;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(name = MicroServiceConstatns.SYSTEM_ADMIN, fallbackFactory = SystemAdminAPIFallback.class)
@RequestMapping(value = "/admin/auth")
public interface SystemAdminAPI {

    @GetMapping(value = "user-permissions")
    ApiResult<JSONArray> getUserPermissions();

    @GetMapping(value = "all-platform-permissions")
    ApiResult<JSONObject> getAllPlatformPermissions();
}
