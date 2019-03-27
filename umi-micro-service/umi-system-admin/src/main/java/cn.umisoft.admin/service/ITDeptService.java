package cn.umisoft.admin.service;

import cn.umisoft.admin.entity.TDept;
import cn.umisoft.web.service.IUmiService;

import java.util.List;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
public interface ITDeptService extends IUmiService<TDept> {
    /**
     * @description: <p>根据ID查询tree结构数据</p>
     *               <p>不传递id时,或只传递了一个null或''，则查询的是所有的部门tree结构</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/6 5:07 PM
     * @param: id, 支持多个
     * @return: List<TDept>
     */
    List<TDept> queryTree(String... ids);

    /**
     * @description: <p>根据角色ID，查询该角色下的所有直接分配的部门信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    List<TDept> findAllByRoleId(String roleId);
    /**
     * @description: <p>根据用户ID，查询该用户下的所有直接分配的部门信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    List<TDept> findAllByUserId(String userId);

}
