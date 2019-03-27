package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.RUserDept;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 用户-部门中间表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface RUserDeptRepository extends JpaRepository<RUserDept, String> {
}
