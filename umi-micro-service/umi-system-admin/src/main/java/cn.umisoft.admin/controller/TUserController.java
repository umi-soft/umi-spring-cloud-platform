package cn.umisoft.admin.controller;


import cn.umisoft.admin.entity.TUser;
import cn.umisoft.admin.properties.SystemAdminProperties;
import cn.umisoft.admin.service.ITDeptService;
import cn.umisoft.admin.service.ITGroupService;
import cn.umisoft.admin.service.ITRoleService;
import cn.umisoft.admin.service.ITUserService;

import cn.umisoft.util.file.UmiFile;
import cn.umisoft.web.controller.UmiTController;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@RestController
@RequestMapping(value = "/admin/user", name = "用户信息控制器")
public class TUserController extends UmiTController<ITUserService, TUser> {

    @Autowired
    protected SystemAdminProperties properties;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    protected ITRoleService roleService;
    @Autowired
    protected ITDeptService deptService;
    @Autowired
    protected ITGroupService groupService;
    /**
     * @description: <p>覆盖父类方法，密码要求加密</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 10:12 PM
     * @param: HttpServletRequest request
     * @param: TUser entity
     * @return: ApiResult
     */
    @Override
    @PostMapping(value = "add", name = "单个新增")
    public ApiResult add(HttpServletRequest request, TUser entity) {
        entity.setPassword(DigestUtils.md5DigestAsHex(entity.getPassword().getBytes()));
        return super.add(request, entity);
    }

    /**
     * @description: <p>覆盖父类方法，密码要求单独修改</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 10:10 PM
     * @param: HttpServletRequest request
     * @param: TUser entity
     * @return: ApiResult
     */
    @Override
    @PostMapping(value = "edit", name = "根据ID更新用户基础信息")
    public ApiResult edit(HttpServletRequest request, TUser entity) {
        entity.setPassword(null);
        return super.edit(request, entity);
    }

    @PostMapping(value = "check-login-name", name = "判断用户登录名是否已存在")
    public ApiResult checkLoginName(HttpServletRequest request, String id, String loginName) {
        List<TUser> users = this.baseService.findAllByLoginName(loginName);
        JSONObject result = new JSONObject();
        result.put("exist", 0);
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).getId().equals(id)) {
                result.put("exist", 1);
                break;
            }
        }
        return ApiResultWrapper.success(result);
    }

    @PostMapping(value = "edit-password", name = "根据ID修改用户密码")
    public ApiResult editPassword(HttpServletRequest request, String id, String password) {
        return ApiResultWrapper.success(this.baseService.updatePasswordById(id, password));
    }

    @PostMapping(value = "upload-avatar")
    public ApiResult uploadAvatar(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws IOException {
        JSONObject result = new JSONObject();
        result.put("fileName", null);
        result.put("avatarNetAccessPrefixPath", null);
        if (!file.isEmpty()) {
            // 文件名
            String fileName = file.getOriginalFilename();
            // 新文件名
            fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            File dest = new File(UmiFile.addSeparator(properties.getAvatarDiskPrefixPath()) + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            result.put("fileName", fileName);
        }
        return ApiResultWrapper.success(result);
    }

    @GetMapping(value = "avatar", name = "查看用户肖像")
    @ResponseBody
    public ResponseEntity<?> getAvatar(String avatar) {
        return ResponseEntity.ok(resourceLoader.getResource("file:" + UmiFile.addSeparator(properties.getAvatarDiskPrefixPath()) + avatar));
    }

    @GetMapping(value = "all-roles", name = "根据用户ID，查询该用户直接关联的角色信息")
    public ApiResult allRoles(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.roleService.findAllByUserId(id));
    }

    @GetMapping(value = "all-depts", name = "根据用户ID，查询该用户直接关联的部门信息")
    public ApiResult allDepts(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.deptService.findAllByUserId(id));
    }

    @GetMapping(value = "all-user-groups", name = "根据用户ID，查询该用户直接关联的用户组信息")
    public ApiResult allGroups(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.groupService.findAllUserGroupByUserId(id));
    }

    @GetMapping(value = "all-role-groups", name = "根据用户ID，查询该用户直接关联的角色组信息")
    public ApiResult allRoleGroups(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.groupService.findAllRoleGroupByUserId(id));
    }
}