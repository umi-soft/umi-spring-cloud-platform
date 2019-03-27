package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.RUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 用户-角色中间表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface RUserRoleRepository extends JpaRepository<RUserRole, String> {
}
