package cn.umisoft.admin.controller;


import cn.umisoft.admin.entity.TSecurity;
import cn.umisoft.admin.service.ITSecurityService;

import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import cn.umisoft.util.enums.MicroServiceEnum;
import cn.umisoft.web.controller.UmiTController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    /**
     * @description: <p>获取所有的服务信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/2 2:14 PM
     */
    @GetMapping(value = "query-all-micro-service", name = "查询所有的服务列表")
    public ApiResult userAuthorities(){
        JSONArray result = new JSONArray();
        for (MicroServiceEnum microService : MicroServiceEnum.values()) {
            JSONObject temp = new JSONObject();
            temp.put("key", microService.getId());
            temp.put("value", microService.getName());
            result.add(temp);
        }
        return ApiResultWrapper.success(result);
    }

    @PostMapping(value = "init-micro-service-security", name = "初始化某服务的所有系统内置的API权限资源信息")
    public ApiResult initAuthorities(@RequestBody List<TSecurity> entities){
        this.baseService.initAuthorities(entities);
        return ApiResultWrapper.success();
    }
}

