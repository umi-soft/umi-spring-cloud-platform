package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.RUserDept;
import cn.umisoft.admin.service.IRUserDeptService;

import cn.umisoft.web.controller.UmiRController;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户-部门中间表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-14
 */
@RestController
@RequestMapping("/admin/user-dept")
public class RUserDeptController extends UmiRController<IRUserDeptService, RUserDept> {
    public RUserDeptController() {
        this.mappingFields.add("userId");
        this.mappingFields.add("deptId");
    }
}
