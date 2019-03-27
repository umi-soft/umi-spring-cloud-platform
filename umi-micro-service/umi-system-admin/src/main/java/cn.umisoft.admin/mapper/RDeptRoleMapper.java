package cn.umisoft.admin.mapper;

import cn.umisoft.admin.entity.RDeptRole;
import cn.umisoft.web.mapper.UmiMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 部门-角色，中间表 Mapper 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-19
 */
@Mapper
public interface RDeptRoleMapper extends UmiMapper<RDeptRole> {

}
