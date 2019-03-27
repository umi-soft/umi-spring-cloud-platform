package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.RUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 用户-分组中间表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface RUserGroupRepository extends JpaRepository<RUserGroup, String> {
}
