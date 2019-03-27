package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.TDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 部门信息表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface TDeptRepository extends JpaRepository<TDept, String> {
    /**
     * @description: <p>根据ID查询tree结构数据</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/6 5:18 PM
     * @param: id, 支持多个
     *  @return: List<TDept>
     */
    List<TDept> findTDeptsByIdIn(String[] ids);
    /**
     * @description: <p>根据指定的parentId列表，查询符合条件的id数据数组</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/6 11:58 PM
     * @param: parentIds
     * @return: String[]
     */
    @Query(value = "SELECT id FROM TDept WHERE parentId in (:parentIds)")
    String[] findAllIdsByParentId(@Param(value = "parentIds") String[] parentIds);
    /**
     * @description: <p>根据角色ID，查询该角色下的所有直接分配的部门信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    @Query("from TDept where id in ( select deptId from RDeptRole where roleId = ?1)")
    List<TDept> findAllByRoleId(String roleId);
    /**
     * @description: <p>根据用户ID，查询该用户下的所有直接分配的部门信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    @Query("from TDept where id in ( select deptId from RUserDept where userId = ?1)")
    List<TDept> findAllByUserId(String userId);
}
