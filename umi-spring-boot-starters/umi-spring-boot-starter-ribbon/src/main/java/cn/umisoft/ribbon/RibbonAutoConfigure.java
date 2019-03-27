package cn.umisoft.ribbon;

import org.springframework.cloud.netflix.ribbon.DefaultPropertiesFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: <p>Ribbon扩展配置类</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/3/26 5:00 PM
 */
@Configuration
public class RibbonAutoConfigure  {
    @Bean
    public DefaultPropertiesFactory defaultPropertiesFactory() {
        return new DefaultPropertiesFactory();
    }
}
