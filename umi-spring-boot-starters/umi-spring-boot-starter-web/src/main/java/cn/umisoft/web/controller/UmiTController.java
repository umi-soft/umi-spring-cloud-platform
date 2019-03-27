package cn.umisoft.web.controller;

import cn.umisoft.web.entity.UmiEntity;
import cn.umisoft.web.service.IUmiService;
import cn.umisoft.web.util.ApiResult;
import cn.umisoft.web.util.ApiResultWrapper;
import cn.umisoft.web.util.UmiPage;
import cn.umisoft.web.util.UmiQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: <p>仅仅面向实体表，如用户表</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/1/24 10:21 AM
 */
public class UmiTController<S extends IUmiService<T>, T extends UmiEntity> extends UmiController<S,T> {

    @Autowired
    protected S baseService;

    /**
     * @description: <p>单个新增</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/14 12:43 AM
     * @param: request
     * @param: entity
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "add")
    public ApiResult add(HttpServletRequest request, T entity){
        baseService.save(entity);
        return ApiResultWrapper.success(entity);
    }
    /**
     * @description: <p>根据id删除</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/24 10:36 AM
     * @param: request
     * @param: id
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "del")
    public ApiResult del(HttpServletRequest request, String id){
        baseService.removeById(id);
        return ApiResultWrapper.success();
    }
    /**
     * @description: <p>单个修改</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/14 12:43 AM
     * @param: request
     * @param: <p>entity实体,若仅修改某几个字段，则可以将其他字段设置为null即可</p>
     *         <p>null与空字符串的说明</p>
     *         <p>null --> 无变化</p>
     *         <p>"" --> ""</p>
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "edit")
    public ApiResult edit(HttpServletRequest request, T entity){
        entity.setCreatedBy(null);
        entity.setCreatedDate(null);
        baseService.updateById(entity);
        return ApiResultWrapper.success(entity);
    }
    /**
     * @description: <p>根据id查询</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/24 10:36 AM
     * @param: request
     * @param: id
     * @return: ApiResult，数据结构满足开发规范
     */
    @GetMapping(value = "query-by-id")
    public ApiResult queryById(HttpServletRequest request, String id){
        return ApiResultWrapper.success(baseService.getById(id));
    }
    /**
     * @description: <p>根据条件查询全部数据</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/24 10:36 AM
     * @param: request
     * @param: queryConditions
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "query-all")
    public ApiResult queryAll(HttpServletRequest request, @RequestBody(required = false) List<UmiQueryCondition> queryConditions){
        return ApiResultWrapper.success(baseService.list(queryWrapperBuilder(queryConditions)));
    }
    /**
     * @description: <p>根据条件分页查询数据</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/24 10:36 AM
     * @param: request
     * @param: page
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "query-page")
    public ApiResult queryPage(HttpServletRequest request, @RequestBody UmiPage<T> page){
        return ApiResultWrapper.success(baseService.page(page, queryWrapperBuilder(page.getFilters())));
    }
}
