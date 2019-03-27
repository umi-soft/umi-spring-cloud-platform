package cn.umisoft.admin.controller;


import cn.umisoft.admin.entity.TSecurity;
import cn.umisoft.admin.service.ITSecurityService;

import cn.umisoft.web.controller.UmiTController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 安全资源定义信息表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@RestController
@RequestMapping("/admin/security")
public class TSecurityController extends UmiTController<ITSecurityService, TSecurity> {

}
