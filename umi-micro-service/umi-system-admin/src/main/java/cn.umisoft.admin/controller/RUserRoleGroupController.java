package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.RUserRoleGroup;
import cn.umisoft.admin.service.IRUserRoleGroupService;

import cn.umisoft.web.controller.UmiRController;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户-角色分组，中间表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-16
 */
@RestController
@RequestMapping(value = "/admin/user-role-group", name = "部门-角色组中间表控制器")
public class RUserRoleGroupController extends UmiRController<IRUserRoleGroupService, RUserRoleGroup> {
    public RUserRoleGroupController() {
        this.mappingFields.add("userId");
        this.mappingFields.add("roleGroupId");
    }
}
