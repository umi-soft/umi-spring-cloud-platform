package cn.umisoft.web.controller;

import cn.umisoft.web.entity.UmiEntity;
import cn.umisoft.web.service.IUmiService;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: <p>仅仅面向关系表，如用户部门中间表</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/1/24 10:20 AM
 */
public class UmiRController<S extends IUmiService<T>, T extends UmiEntity> extends UmiController<S,T> {

    @Autowired
    protected S baseService;

    /**
     * @description: <p>单个新增，仅追加</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/14 12:43 AM
     * @param: request
     * @param: entity
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "add")
    public ApiResult add(HttpServletRequest request, T entity){
        checkMappingFields(entity);
        baseService.save(entity);
        return ApiResultWrapper.success();
    }

    /**
     * @description: <p>批量重置，清除原有映射关系</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/14 12:44 AM
     * @param: request
     * @param: entities
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping("reset")
    public ApiResult resetAdd(HttpServletRequest request, @RequestBody List<T> entities){
        Map<String, Set<Object>> mappingInfo = new HashMap<String, Set<Object>>();
        for (T entity : entities) {
            for (String mappingField: mappingFields) {
                if (mappingInfo.get(mappingField) == null) {
                    mappingInfo.put(mappingField, new HashSet<Object>());
                }
                mappingInfo.get(mappingField).add(getEntityFieldValue(entity, mappingField));
            }
        }
        baseService.resetByMap(entities, mappingInfo);
        return ApiResultWrapper.success();
    }

    /**
     * @description: <p>根据映射关系删除</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/14 12:45 AM
     * @param: request
     * @param: mapping
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "del-by-entity-mapping")
    public ApiResult delByEntityMapping(HttpServletRequest request, T entity){
        Map<String, Object> mapping = new HashMap<String, Object>();
        for (String mappingField: mappingFields) {
            mapping.put(mappingField, getEntityFieldValue(entity, mappingField));
        }
        baseService.removeByMap(mapping);
        return ApiResultWrapper.success();
    }
}
