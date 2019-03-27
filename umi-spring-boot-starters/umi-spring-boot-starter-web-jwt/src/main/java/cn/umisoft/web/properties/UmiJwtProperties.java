package cn.umisoft.web.properties;

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
public class UmiJwtProperties {
    String [] excludePathPatterns = {"/admin/auth/captcha", "/admin/auth/login"};
    String secret = "umisoft.cn";
    Integer minutes = 30;
}
