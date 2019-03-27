package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.RUserGroup;
import cn.umisoft.admin.service.IRUserGroupService;

import cn.umisoft.web.controller.UmiRController;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户-分组中间表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-14
 */
@RestController
@RequestMapping("/admin/user-group")
public class RUserGroupController extends UmiRController<IRUserGroupService, RUserGroup> {
    public RUserGroupController() {
        this.mappingFields.add("userId");
        this.mappingFields.add("groupId");
    }
}
