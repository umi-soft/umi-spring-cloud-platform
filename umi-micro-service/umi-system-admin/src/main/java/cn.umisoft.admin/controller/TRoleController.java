package cn.umisoft.admin.controller;


import cn.umisoft.admin.entity.TRole;
import cn.umisoft.admin.service.*;

import cn.umisoft.web.controller.UmiTController;
import cn.umisoft.web.util.ApiResult;
import cn.umisoft.web.util.ApiResultWrapper;
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
@RequestMapping("/admin/role/")
public class TRoleController extends UmiTController<ITRoleService, TRole> {

    @Autowired
    protected ITMenuService menuService;
    @Autowired
    protected ITUserService userService;
    @Autowired
    protected ITDeptService deptService;
    @Autowired
    protected ITGroupService groupService;

    @GetMapping(value = "all-menus")
    public ApiResult allMenus(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.menuService.findAllByRoleId(id));
    }

    @GetMapping(value = "all-users")
    public ApiResult allUsers(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.userService.findAllByRoleId(id));
    }

    @GetMapping(value = "all-depts")
    public ApiResult allDepts(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.deptService.findAllByRoleId(id));
    }

    @GetMapping(value = "all-role-groups")
    public ApiResult allGroups(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.groupService.findAllRoleGroupByRoleId(id));
    }
}