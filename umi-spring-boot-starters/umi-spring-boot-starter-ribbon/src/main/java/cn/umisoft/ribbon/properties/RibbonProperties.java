package cn.umisoft.ribbon.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "umisoft.ribbon")
@Setter
@Getter
public class RibbonProperties {

}
