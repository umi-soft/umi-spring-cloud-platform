package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.TMenu;
import cn.umisoft.admin.service.ITMenuService;

import cn.umisoft.admin.service.ITRoleService;
import cn.umisoft.admin.service.ITSecurityService;
import cn.umisoft.web.controller.UmiTController;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端路由菜单信息表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@RestController
@RequestMapping("/admin/menu")
public class TMenuController extends UmiTController<ITMenuService, TMenu> {

    @Autowired
    protected ITSecurityService securityService;
    @Autowired
    protected ITRoleService roleService;

    @PostMapping(value = "sync")
    public ApiResult sync(HttpServletRequest request, @RequestBody List<TMenu> menus) {
        return ApiResultWrapper.success(this.baseService.syncMenus(menus));
    }

    @GetMapping(value = "all-securities")
    public ApiResult allSecurities(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.securityService.findAllByMenuId(id));
    }

    @GetMapping(value = "all-roles")
    public ApiResult allRoles(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.roleService.findAllByMenuId(id));
    }
}
