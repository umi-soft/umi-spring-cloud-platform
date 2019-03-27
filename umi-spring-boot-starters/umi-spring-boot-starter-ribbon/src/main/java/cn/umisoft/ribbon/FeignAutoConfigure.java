package cn.umisoft.ribbon;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: <p>Feign配置</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/3/26 4:49 PM
 */
@Configuration
public class FeignAutoConfigure {

    /**
     * @description: <p>Feign 日志级别</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/26 5:00 PM
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
