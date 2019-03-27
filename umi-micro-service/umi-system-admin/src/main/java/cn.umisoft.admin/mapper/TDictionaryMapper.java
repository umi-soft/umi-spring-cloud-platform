package cn.umisoft.admin.mapper;

import cn.umisoft.admin.entity.TDictionary;
import cn.umisoft.web.mapper.UmiMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 业务字典（含业务字典分类）表 Mapper 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@Mapper
public interface TDictionaryMapper extends UmiMapper<TDictionary> {

}
