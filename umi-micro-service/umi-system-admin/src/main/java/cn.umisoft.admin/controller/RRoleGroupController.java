package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.RRoleGroup;
import cn.umisoft.admin.service.IRRoleGroupService;

import cn.umisoft.web.controller.UmiRController;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色-分组中间表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-16
 */
@RestController
@RequestMapping("/admin/role-group")
public class RRoleGroupController extends UmiRController<IRRoleGroupService, RRoleGroup> {
    public RRoleGroupController() {
        this.mappingFields.add("groupId");
        this.mappingFields.add("roleId");
    }
}
