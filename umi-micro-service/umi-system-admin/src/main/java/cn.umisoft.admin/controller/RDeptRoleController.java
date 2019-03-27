package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.RDeptRole;
import cn.umisoft.admin.service.IRDeptRoleService;
import cn.umisoft.web.controller.UmiRController;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门-角色，中间表 前端控制器
 * </p>
 * @author hujie@umisoft.cn
 * @since 2019-01-19
 */
@RestController
@RequestMapping("/admin/dept-role")
public class RDeptRoleController extends UmiRController<IRDeptRoleService, RDeptRole> {
    public RDeptRoleController() {
        this.mappingFields.add("deptId");
        this.mappingFields.add("roleId");
    }
}
