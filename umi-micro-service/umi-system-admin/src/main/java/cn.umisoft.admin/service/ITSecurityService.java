package cn.umisoft.admin.service;

import cn.umisoft.admin.dto.SecurityRoleDTO;
import cn.umisoft.admin.entity.TSecurity;
import cn.umisoft.web.service.IUmiService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 安全资源定义信息表 服务类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
public interface ITSecurityService extends IUmiService<TSecurity> {
    /**
     * @description: <p>根据路由菜单ID，查看该路由菜单直接分配的所有后端资源权限列表</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:11 AM
     */
    List<TSecurity> findAllByMenuId(String menuId);
    /**
     * @description: <p>返回系统中包含的所有资源与角色的映射关系</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/4 6:31 PM
     */
    Map<String, Set<String>> findAllSecurityRoleMapping();
}
