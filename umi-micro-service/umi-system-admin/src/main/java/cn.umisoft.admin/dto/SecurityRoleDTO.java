package cn.umisoft.admin.dto;

import cn.umisoft.admin.entity.TSecurity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class SecurityRoleDTO extends TSecurity {
    private Set<String> roles;
}
