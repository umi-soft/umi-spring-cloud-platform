package cn.umisoft.admin.dto;

import cn.umisoft.admin.entity.TRole;
import cn.umisoft.admin.entity.TSecurity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class SecurityRoleDTO extends TSecurity {
    // TODO 思考获取的思路，是基于mybaties xml做合适还是jpa
    private Set<TRole> roles;
}
