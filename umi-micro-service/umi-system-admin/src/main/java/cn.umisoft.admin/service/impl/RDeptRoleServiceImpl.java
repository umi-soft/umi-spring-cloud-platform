package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.RDeptRole;
import cn.umisoft.admin.mapper.RDeptRoleMapper;
import cn.umisoft.admin.repository.RDeptRoleRepository;
import cn.umisoft.admin.service.IRDeptRoleService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门-角色，中间表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class RDeptRoleServiceImpl extends UmiServiceImpl<RDeptRoleMapper, RDeptRoleRepository, RDeptRole> implements IRDeptRoleService {

}
