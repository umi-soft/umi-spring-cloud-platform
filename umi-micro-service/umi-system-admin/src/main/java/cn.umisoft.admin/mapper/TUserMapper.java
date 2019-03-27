package cn.umisoft.admin.mapper;

import cn.umisoft.admin.entity.TUser;
import cn.umisoft.web.mapper.UmiMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@Mapper
public interface TUserMapper extends UmiMapper<TUser> {

}
