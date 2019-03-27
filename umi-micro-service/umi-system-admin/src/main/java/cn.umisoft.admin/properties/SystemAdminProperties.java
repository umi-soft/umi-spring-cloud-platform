package cn.umisoft.admin.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
@Setter
@Getter
public class SystemAdminProperties {
    private String avatarDiskPrefixPath;
}
