package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.RMenuRole;
import cn.umisoft.admin.service.IRMenuRoleService;

import cn.umisoft.web.controller.UmiRController;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 菜单-角色中间表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-14
 */
@RestController
@RequestMapping(value = "/admin/menu-role", name = "菜单-角色中间表控制器")
public class RMenuRoleController extends UmiRController<IRMenuRoleService, RMenuRole> {
    public RMenuRoleController() {
        this.mappingFields.add("menuId");
        this.mappingFields.add("roleId");
    }
}
