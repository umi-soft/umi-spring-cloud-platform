package cn.umisoft.admin.controller;


import cn.umisoft.admin.entity.TSecurity;
import cn.umisoft.admin.service.ITSecurityService;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import cn.umisoft.web.controller.UmiTController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 安全资源定义信息表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@RestController
@RequestMapping(value = "/admin/security", name = "服务API资源表控制器")
public class TSecurityController extends UmiTController<ITSecurityService, TSecurity> {

    @PostMapping(value = "init-micro-service-security", name = "初始化某服务的所有系统内置的API权限资源信息")
    public ApiResult initAuthorities(@RequestBody List<TSecurity> entities){
        this.baseService.initAuthorities(entities);
        return ApiResultWrapper.success();
    }
}

