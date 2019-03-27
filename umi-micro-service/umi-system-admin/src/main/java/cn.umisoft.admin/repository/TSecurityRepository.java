package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.TSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 安全资源定义信息表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface TSecurityRepository extends JpaRepository<TSecurity, String> {
    /**
     * @description: <p>根据路由菜单ID，查看该路由菜单直接分配的所有后端资源权限列表</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/7 12:11 AM
     */
    @Query("from TSecurity where id in ( select securityId from RMenuSecurity where menuId = ?1)")
    List<TSecurity> findAllByMenuId(String menuId);
}
