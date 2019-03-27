package cn.umisoft.admin.service;

import cn.umisoft.admin.entity.TRole;
import cn.umisoft.web.service.IUmiService;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
public interface ITRoleService extends IUmiService<TRole> {
    /**
     * @description: <p>查询当前用户直接以及简洁具有的所有角色ID，用户的角色来源可分为：</p>
     *               <p>用户可直接授予角色</p>
     *               <p>用户可直接授予角色组，角色组可直接授予角色</p>
     *               <p>用户可直接授予部门，部门可直接授予角色</p>
     *               <p>用户可直接授予用户组，用户组可直接授予角色组，角色组可直接授予角色</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:11 AM
     */
    HashSet<String> findAllByCurrentUserId();
    /**
     * @description: <p>根据部门ID，查看该部门直接分配的所有角色列表</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:11 AM
     */
    List<TRole> findAllByDeptId(String deptId);
    /**
     * @description: <p>根据用户ID，查看该用户直接分配的所有角色列表</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:11 AM
     */
    List<TRole> findAllByUserId(String userId);
    /**
     * @description: <p>根据路由菜单ID，查看该路由菜单直接分配的所有角色列表</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:11 AM
     */
    List<TRole> findAllByMenuId(String menuId);
    /**
     * @description: <p>根据角色组ID，查询角色组下直接分配的角色信息</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/14 11:56 PM
     */
    List<TRole> findAllByRoleGroupId(String roleGroupId);
}
