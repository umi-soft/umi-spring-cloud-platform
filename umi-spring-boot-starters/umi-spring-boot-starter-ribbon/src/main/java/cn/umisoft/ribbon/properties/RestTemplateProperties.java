package cn.umisoft.ribbon.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "umisoft.http-client")
@Setter
@Getter
public class RestTemplateProperties {
    Integer maxTotal = 200;

    Integer maxPerRoute = 50;

    Integer readTimeout = 35000;

    Integer connectTimeout = 10000;

    Integer connectionRequestTimeout = 35000;

    Integer retryCount = 3;
}
