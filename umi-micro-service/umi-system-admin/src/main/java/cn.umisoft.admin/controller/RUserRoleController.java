package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.RUserRole;
import cn.umisoft.admin.service.IRUserRoleService;

import cn.umisoft.web.controller.UmiRController;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户-角色中间表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-14
 */
@RestController
@RequestMapping("/admin/user-role")
public class RUserRoleController extends UmiRController<IRUserRoleService, RUserRole> {
    public RUserRoleController() {
        this.mappingFields.add("userId");
        this.mappingFields.add("roleId");
    }
}
