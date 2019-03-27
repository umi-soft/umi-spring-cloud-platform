package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.RDeptRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 部门-角色，中间表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-19
 */
public interface RDeptRoleRepository extends JpaRepository<RDeptRole, String> {
}
