package cn.umisoft.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;

import cn.umisoft.gateway.feign.fallback.SystemAdminServiceFallback;
import cn.umisoft.util.constatns.MicroServiceConstatns;

@FeignClient(name = MicroServiceConstatns.SYSTEM_ADMIN, fallback = SystemAdminServiceFallback.class)
public interface SystemAdminService {

}
