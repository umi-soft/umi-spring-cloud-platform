package cn.umisoft.admin.controller;


import cn.umisoft.admin.entity.TRole;
import cn.umisoft.admin.service.*;

import cn.umisoft.web.controller.UmiTController;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@RestController
@RequestMapping(value = "/admin/role/", name = "角色表控制器")
public class TRoleController extends UmiTController<ITRoleService, TRole> {

    @Autowired
    protected ITMenuService menuService;
    @Autowired
    protected ITUserService userService;
    @Autowired
    protected ITDeptService deptService;
    @Autowired
    protected ITGroupService groupService;

    @GetMapping(value = "all-menus", name = "根据角色ID，查询该角色直接关联的所有前端路由信息")
    public ApiResult allMenus(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.menuService.findAllByRoleId(id));
    }

    @GetMapping(value = "all-users", name = "根据角色ID，查询该角色直接关联的所有用户信息")
    public ApiResult allUsers(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.userService.findAllByRoleId(id));
    }

    @GetMapping(value = "all-depts", name = "根据角色ID，查询该角色直接关联的所有部门信息")
    public ApiResult allDepts(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.deptService.findAllByRoleId(id));
    }

    @GetMapping(value = "all-role-groups", name = "根据角色ID，查询该角色直接关联的所有角色组信息")
    public ApiResult allGroups(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.groupService.findAllRoleGroupByRoleId(id));
    }
}