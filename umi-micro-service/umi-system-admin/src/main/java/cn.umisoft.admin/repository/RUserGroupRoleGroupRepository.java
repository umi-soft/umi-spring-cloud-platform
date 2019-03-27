package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.RUserGroupRoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 用户分组-角色分组，中间表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-16
 */
public interface RUserGroupRoleGroupRepository extends JpaRepository<RUserGroupRoleGroup, String> {
}
