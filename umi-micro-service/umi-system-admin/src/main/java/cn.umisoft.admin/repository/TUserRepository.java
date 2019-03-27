package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 用户信息表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface TUserRepository extends JpaRepository<TUser, String> {
    /**
     * @description: <p>根据登录名称，模糊查询满足的用户</p>
     * @param loginName 登录显示名称
     * @return List<TUser>
     */
    List<TUser> findAllByLoginNameIgnoreCase(String loginName);
    /**
     * @description: <p>根据部门ID，查询该部门下的所有用户信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    @Query("from TUser where id in ( select userId from RUserDept where deptId = ?1)")
    List<TUser> findAllByDeptId(String deptId);
    /**
     * @description: <p>根据 角色ID，查询该角色下的直接分配的所有用户信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    @Query("from TUser where id in ( select userId from RUserRole where roleId = ?1)")
    List<TUser> findAllByRoleId(String roleId);
    /**
     * @description: <p>根据 用户组ID，查询该用户组下的直接分配的所有用户信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    @Query("from TUser where id in ( select userId from RUserGroup where groupId = ?1)")
    List<TUser> findAllByUserGroupId(String userGroupId);

    /**
     * @description: <p>根据 角色组ID，查询该角色组下的直接分配的所有用户信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    @Query("from TUser where id in ( select userId from RUserRoleGroup where roleGroupId = ?1)")
    List<TUser> findAllByRoleGroupId(String roleGroupId);
}
