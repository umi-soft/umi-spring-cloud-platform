package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.RMenuRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 菜单-角色中间表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface RMenuRoleRepository extends JpaRepository<RMenuRole, String> {
}
