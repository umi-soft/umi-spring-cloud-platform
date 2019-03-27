package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.TMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 前端路由菜单信息表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface TMenuRepository extends JpaRepository<TMenu, String> {
    /**
     * @description: <p>根据角色ID查询该角色直接分配的前端路由菜单权限</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 7:11 PM
     * @param: roleId
     * @return: List<TMenu>
     */
    @Query("from TMenu where id in ( select menuId from RMenuRole where roleId = ?1)")
    List<TMenu> findAllByRoleId(String roleId);
}
