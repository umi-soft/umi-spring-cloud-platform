package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.RUserRoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 用户-角色分组，中间表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-16
 */
public interface RUserRoleGroupRepository extends JpaRepository<RUserRoleGroup, String> {
}
