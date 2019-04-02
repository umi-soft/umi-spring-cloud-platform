package cn.umisoft.gateway;

import cn.umisoft.gateway.config.SpringCloudGatewayProperties;
import cn.umisoft.util.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties({ SpringCloudGatewayProperties.class, JwtProperties.class })
@EnableFeignClients({"cn.umisoft.gateway.feign"})
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

}
