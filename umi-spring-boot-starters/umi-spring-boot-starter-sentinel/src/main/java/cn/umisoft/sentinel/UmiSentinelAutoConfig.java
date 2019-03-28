package cn.umisoft.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UmiSentinelAutoConfig {
    public UmiSentinelAutoConfig() {
        WebCallbackManager.setUrlBlockHandler(new UmiSentinelUrlBlockHandler());
    }
}
