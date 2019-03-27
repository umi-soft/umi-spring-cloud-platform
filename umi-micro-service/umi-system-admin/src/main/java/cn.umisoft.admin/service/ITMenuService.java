package cn.umisoft.admin.service;

import cn.umisoft.admin.entity.TMenu;
import cn.umisoft.web.service.IUmiService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 前端路由菜单信息表 服务类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
public interface ITMenuService extends IUmiService<TMenu> {
    /**
     * @description: <p>根据角色ID查询该角色直接分配的前端路由菜单权限</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 7:11 PM
     * @param: roleId
     * @return: List<TMenu>
     */
    List<TMenu> findAllByRoleId(String roleId);
    /**
     * @description: <p>同步前端路由菜单，清除历史数据，保留本次同步的数据</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/13 6:39 PM
     * @param:
     * @return:
     */
    Map<String, List<Map<String, String>>> syncMenus(List<TMenu> menus);
    /**
     * @description: <p>查询系统中所有的路由与角色映射关系</p>
     *               <p>map key为路由ID，value为映射的角色ID set集合</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/14 2:11 PM
     */
    Map<String, Set<String>> findAllRouterRoles();
}
