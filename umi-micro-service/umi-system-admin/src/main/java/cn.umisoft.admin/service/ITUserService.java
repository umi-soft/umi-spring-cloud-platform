package cn.umisoft.admin.service;

import cn.umisoft.admin.entity.TUser;
import cn.umisoft.web.service.IUmiService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
public interface ITUserService extends IUmiService<TUser> {
    /**
     * @description: <p>修改用户密码</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/9 3:54 PM
     * @param: id
     * @param: password
     * @return: Boolean
     */
    Boolean updatePasswordById(String id, String password);
    /**
     * @description: <p>根据用户名和密码登录</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 3:54 PM
     * @param: loginName
     * @param: password
     * @return: TUser
     */
    TUser login(String loginName, String password);
    /**
     * @description: <p>根据登录名称，模糊查询满足的用户，排出已删除的用户</p>
     * @param loginName 登录显示名称
     * @return List<TUser>
     */
    List<TUser> findAllByLoginName(String loginName);
    /**
     * @description: <p>根据部门id,查询部门下的所有直接授予的可用用户</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 4:09 PM
     * @param: deptId
     * @return: List<TUser>
     */
    List<TUser> findAllByDeptId(String deptId);
    /**
     * @description: <p>根据 角色ID，查询该角色下的直接分配的所有用户信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    List<TUser> findAllByRoleId(String roleId);
    /**
     * @description: <p>根据 用户组ID，查询该用户组下的直接分配的所有用户信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    List<TUser> findAllByUserGroupId(String userGroupId);
    /**
     * @description: <p>根据 角色组ID，查询该角色组下的直接分配的所有用户信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:09 AM
     */
    List<TUser> findAllByRoleGroupId(String roleGroupId);
}
