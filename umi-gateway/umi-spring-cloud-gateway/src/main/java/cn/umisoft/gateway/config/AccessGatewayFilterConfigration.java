package cn.umisoft.gateway.config;

import cn.umisoft.gateway.filter.AccessGatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessGatewayFilterConfigration {
    /**
     * @description: <p>全局网关层token移除，便于fegin准确追加后端服务隔离层token</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/8 3:43 PM
     */
    @Bean
    AccessGatewayFilter accessGatewayFilter() {
        return new AccessGatewayFilter();
    }
}
