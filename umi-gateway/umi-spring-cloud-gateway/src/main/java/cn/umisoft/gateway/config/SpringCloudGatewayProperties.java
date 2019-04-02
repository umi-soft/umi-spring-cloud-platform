package cn.umisoft.gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("umisoft.gateway")
public class SpringCloudGatewayProperties {

}
