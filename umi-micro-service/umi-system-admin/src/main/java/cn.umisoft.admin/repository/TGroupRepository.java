package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.TGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 分组信息表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface TGroupRepository extends JpaRepository<TGroup, String> {
    /**
     * @description: <p>根据角色ID，查询该角色下的所有直接分配的分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    @Query("from TGroup where id in ( select groupId from RRoleGroup where roleId = ?1)")
    List<TGroup> findAllRoleGroupByRoleId(String roleId);
    /**
     * @description: <p>根据用户ID，查询该用户下的所有直接分配的分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    @Query("from TGroup where id in ( select groupId from RUserGroup where userId = ?1)")
    List<TGroup> findAllUserGroupByUserId(String userId);
    /**
     * @description: <p>根据用户ID，查询该用户下的所有直接分配的分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    @Query("from TGroup where id in ( select roleGroupId from RUserRoleGroup where userId = ?1)")
    List<TGroup> findAllRoleGroupByUserId(String userId);
    /**
     * @description: <p>根据用户分组ID，查询该用户分组所有直接分配的角色分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 7:43 PM
     * @param: userGroupId
     * @return: List<TGroup>
     */
    @Query("from TGroup where id in ( select roleGroupId from RUserGroupRoleGroup where userGroupId = ?1)")
    List<TGroup> findAllRoleGroupByUserGroupId(String userGroupId);
    /**
     * @description: <p>根据角色分组ID，查询该角色分组所有直接分配的用户分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 7:43 PM
     * @param: roleGroupId
     * @return: List<TGroup>
     */
    @Query("from TGroup where id in ( select userGroupId from RUserGroupRoleGroup where roleGroupId = ?1)")
    List<TGroup> findAllUserGroupByRoleGroupId(String roleGroupId);
}
