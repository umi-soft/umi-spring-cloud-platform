package cn.umisoft.admin.controller;

import cn.umisoft.admin.entity.TDictionary;
import cn.umisoft.admin.service.ITDictionaryService;

import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import cn.umisoft.util.enums.*;
import cn.umisoft.util.enums.Boolean;
import cn.umisoft.web.controller.UmiTController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务字典（含业务字典分类）表 前端控制器
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@RestController
@RequestMapping(value = "/admin/dictionary", name = "字典（或字典分类）表控制器")
public class TDictionaryController extends UmiTController<ITDictionaryService, TDictionary> {
    @GetMapping(value = "query-base-system-dictionaries", name="查询基础系统内置数据字典(源自枚举)")
    public ApiResult queryBaseDictionaries(HttpServletRequest request){
        Map<String, List<Map<String, String>>> dictionaries = new HashMap<String, List<Map<String, String>>>();
        dictionaries.put(SystemDictionaryCategory.REFRESH_TRUE_OR_FALSE.name(), Boolean.getAllDictionaries());
        dictionaries.put(SystemDictionaryCategory.REFRESH_GENDER.name(), Gender.getAllDictionaries());
        dictionaries.put(SystemDictionaryCategory.REFRESH_DICTIONARY_CATEGORY.name(), DictionaryCategory.getAllDictionaries());
        dictionaries.put(SystemDictionaryCategory.REFRESH_MICRO_SERVICE.name(), MicroService.getAllDictionaries());
        return ApiResultWrapper.success(dictionaries);
    }
}
