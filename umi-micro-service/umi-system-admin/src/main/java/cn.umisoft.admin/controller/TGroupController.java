package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.TGroup;
import cn.umisoft.admin.service.ITGroupService;

import cn.umisoft.admin.service.ITRoleService;
import cn.umisoft.admin.service.ITUserService;
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
 * 分组信息表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@RestController
@RequestMapping("/admin/group")
public class TGroupController extends UmiTController<ITGroupService, TGroup> {

    @Autowired
    protected ITUserService userService;
    @Autowired
    protected ITGroupService groupService;
    @Autowired
    protected ITRoleService roleService;

    @GetMapping(value = "all-role-group-users")
    public ApiResult allRoleGroupUsers(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.userService.findAllByRoleGroupId(id));
    }

    @GetMapping(value = "all-user-group-users")
    public ApiResult allUserGroupUsers(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.userService.findAllByUserGroupId(id));
    }

    @GetMapping(value = "all-role-groups")
    public ApiResult allRoleGroups(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.groupService.findAllRoleGroupByUserGroupId(id));
    }

    @GetMapping(value = "all-user-groups")
    public ApiResult allUserGroups(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.groupService.findAllUserGroupByRoleGroupId(id));
    }

    @GetMapping(value = "all-roles")
    public ApiResult allRoles(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.roleService.findAllByRoleGroupId(id));
    }
}
