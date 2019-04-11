package cn.umisoft.web.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: hujie@umisoft.cn
 * @date: 2019/1/21 2:46 PM
 */
// TODO 考虑自动构建一个 返回Map<String, T>的方法，key为T的id
public interface IUmiService<T> extends IService<T> {
    /**
     * @description: <p>根据ID删除，支持记录最后修改人，反射获取Entity实例，通过deleteByIdWithFill删除</p>
     *               <p>覆盖父类方法</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/16 12:22 PM
     * @param: id
     * @return: boolean
     */
    @Override
    public boolean removeById(Serializable id);
    /**
     * @description: <p>删除，该方法将会通过反射手段将entity属性名称转换为数据库属性名称</p>
     *               <p>覆盖父类方法</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/21 4:38 PM
     * @param: map，key值为entity的属性名称，value
     * @return: boolean
     */
    @Override
    public boolean removeByMap(Map<String, Object> map);
    /**
     * @description: <p>重新构建映射关系，清除原有映射关系，只保留本次新增的映射关系,默认批量操作大小设置为1000</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/23 3:00 PM
     * @param: entities 新的实体映射关系
     * @param: mappingInfo <p>实体属性名称键值对，独立生效</p>
     *                      <p>如：[{'roleId': ['1','2']}, {'userId': ['3']}],将先根据roleId = 1删除,然后根据roleId = 2删除，并再次根据userId = 3进行删除</p>
     *                      <p>另：key值传递实体属性名称即可，将自动根据实体注解获取对应的数据库字段名称</p>
     * @return: boolean
     */
    default boolean resetByMap(List<T> entities, Map<String, Set<Object>> mappingInfo) {
        return this.resetByMap(entities, mappingInfo, 1000);
    }
    /**
     * @description: <p>重新构建映射关系，清除原有映射关系，只保留本次新增的映射关系</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/23 3:00 PM
     * @param: entities 新的实体映射关系
     * @param: mappingInfo <p>实体属性名称键值对，独立生效</p>
     *                      <p>如：[{'roleId': ['1','2']}, {'userId': ['3']}],将先根据roleId = 1删除,然后根据roleId = 2删除，并再次根据userId = 3进行删除</p>
     *                      <p>另：key值传递实体属性名称即可，将自动根据实体注解获取对应的数据库字段名称</p>
     * @param: <p>batchSize 设定批量新增的大小，过大可能存在性能问题</p>
     * @return: boolean
     */
    boolean resetByMap(List<T> entities, Map<String, Set<Object>> mappingInfo, int batchSize);
}