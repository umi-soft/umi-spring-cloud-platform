package cn.umisoft.admin.mapper;

import cn.umisoft.admin.dto.SecurityRoleDTO;
import cn.umisoft.admin.entity.TSecurity;
import cn.umisoft.web.mapper.UmiMapper;

import java.util.List;

/**
 * <p>
 * 安全资源定义信息表 Mapper 接口
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
public interface TSecurityMapper extends UmiMapper<TSecurity> {
    List<SecurityRoleDTO> selectAllSecurityRoleDTO();
}
