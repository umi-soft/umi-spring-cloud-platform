package cn.umisoft.feign.remote.fallback;

import cn.umisoft.feign.remote.SystemAdminAPI;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import cn.umisoft.util.enums.MicroService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SystemAdminAPIFallback implements FallbackFactory<SystemAdminAPI> {

    @Override
    public SystemAdminAPI create(Throwable throwable) {
        return new SystemAdminAPI() {

            @Override
            public ApiResult<JSONArray> getUserPermissions() {
                log.error(String.format("服务[%s]调用错误，错误信息：\n%s", MicroService.SYSTEM_ADMIN.name, throwable.getMessage()));
                return ApiResultWrapper.unknown();
            }

            @Override
            public ApiResult<JSONObject> getAllPlatformPermissions() {
                log.error(String.format("服务[%s]调用错误，错误信息：\n%s", MicroService.SYSTEM_ADMIN.name, throwable.getMessage()));
                return ApiResultWrapper.unknown();
            }

            @Override
            public ApiResult<JSONObject> initMicroServiceSecurity(@RequestBody List<Map<String, String>> authorities) {
                log.error(String.format("服务[%s]调用错误，错误信息：\n%s", MicroService.SYSTEM_ADMIN.name, throwable.getMessage()));
                return ApiResultWrapper.unknown();
            }
        };
    }
}
