package cn.umisoft.gateway;

import cn.umisoft.util.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties({ JwtProperties.class })
@EnableFeignClients(basePackages = {"cn.umisoft.gateway.remote"})
public class SpringCloudGatewayStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayStarter.class, args);
    }

}
