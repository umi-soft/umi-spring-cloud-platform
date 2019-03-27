package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.TDictionary;
import cn.umisoft.admin.service.ITDictionaryService;

import cn.umisoft.web.controller.UmiTController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 业务字典（含业务字典分类）表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@RestController
@RequestMapping("/admin/dictionary")
public class TDictionaryController extends UmiTController<ITDictionaryService, TDictionary> {

}
