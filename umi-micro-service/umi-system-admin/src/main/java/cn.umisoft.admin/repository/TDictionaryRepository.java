package cn.umisoft.admin.repository;

import cn.umisoft.admin.entity.TDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 业务字典（含业务字典分类）表 Repository 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface TDictionaryRepository extends JpaRepository<TDictionary, String> {
}
