package cn.umisoft.gateway;

import cn.umisoft.util.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties({ JwtProperties.class })
public class SpringCloudGatewayStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayStarter.class, args);
    }

}
