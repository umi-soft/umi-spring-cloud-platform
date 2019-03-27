package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.RMenuSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 菜单-安全资源中间表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface RMenuSecurityRepository extends JpaRepository<RMenuSecurity, String> {
}
