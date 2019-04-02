package cn.umisoft.util.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author: hujie@umisoft.cn
 * @Date: 2019/3/27 11:19 AM
 */
@ConfigurationProperties(prefix = "umisoft.jwt")
@Setter
@Getter
public class JwtProperties {
    String [] excludePathPatterns = {"/admin/auth/captcha", "/admin/auth/login"};
    String serviceSecret = "umisoft.cn";
    String gatewaySecret = "umisoft.cn";
    Integer minutes = 30;
}
