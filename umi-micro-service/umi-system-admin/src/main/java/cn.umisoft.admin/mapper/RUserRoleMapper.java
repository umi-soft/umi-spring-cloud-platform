package cn.umisoft.admin.mapper;

import cn.umisoft.admin.entity.RUserRole;
import cn.umisoft.web.mapper.UmiMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户-角色中间表 Mapper 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@Mapper
public interface RUserRoleMapper extends UmiMapper<RUserRole> {

}
