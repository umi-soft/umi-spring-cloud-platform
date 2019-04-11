package cn.umisoft.gateway.remote.fallback;

import cn.umisoft.gateway.remote.SystemAdminAPI;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import cn.umisoft.util.enums.MicroServiceEnum;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemAdminAPIFallback implements FallbackFactory<SystemAdminAPI> {

    @Override
    public SystemAdminAPI create(Throwable throwable) {
        return new SystemAdminAPI() {

            @Override
            public ApiResult<JSONArray> getUserPermissions() {
                log.error("服务：[" + MicroServiceEnum.SYSTEM_ADMIN.name + "]调用错误，错误信息：" + throwable.getMessage());
                return ApiResultWrapper.unknown();
            }

            @Override
            public ApiResult<JSONObject> getAllPlatformPermissions() {
                log.error("服务：[" + MicroServiceEnum.SYSTEM_ADMIN.name + "]调用错误，错误信息：" + throwable.getMessage());
                return ApiResultWrapper.unknown();
            }
        };
    }
}
