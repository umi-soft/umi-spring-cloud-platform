package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.RRoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 角色-分组中间表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-16
 */
public interface RRoleGroupRepository extends JpaRepository<RRoleGroup, String> {
}
