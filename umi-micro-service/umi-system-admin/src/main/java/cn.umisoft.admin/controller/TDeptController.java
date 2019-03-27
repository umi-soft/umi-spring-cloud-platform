package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.TDept;
import cn.umisoft.admin.service.ITDeptService;

import cn.umisoft.admin.service.ITRoleService;
import cn.umisoft.admin.service.ITUserService;
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
 * 部门信息表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@RestController
@RequestMapping("/admin/dept")
public class TDeptController extends UmiTController<ITDeptService, TDept> {

    @Autowired
    protected ITUserService userService;
    @Autowired
    protected ITRoleService roleService;

    @GetMapping(value = "query-all-tree")
    public ApiResult queryAllTree(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.baseService.queryTree(id));
    }

    @GetMapping(value = "all-users")
    public ApiResult allUsers(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.userService.findAllByDeptId(id));
    }

    @GetMapping(value = "all-roles")
    public ApiResult allRoles(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.roleService.findAllByDeptId(id));
    }
}
