package cn.umisoft.feign.remote;

import cn.umisoft.feign.remote.fallback.SystemAdminAPIFallback;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {
    @Bean
    public SystemAdminAPIFallback systemAdminAPIFallback() {
        return new SystemAdminAPIFallback();
    }
}
