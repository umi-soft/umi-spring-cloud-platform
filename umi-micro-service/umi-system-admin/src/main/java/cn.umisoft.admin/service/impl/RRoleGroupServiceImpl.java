package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.RRoleGroup;
import cn.umisoft.admin.mapper.RRoleGroupMapper;
import cn.umisoft.admin.repository.RRoleGroupRepository;
import cn.umisoft.admin.service.IRRoleGroupService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-分组中间表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class RRoleGroupServiceImpl extends UmiServiceImpl<RRoleGroupMapper, RRoleGroupRepository, RRoleGroup> implements IRRoleGroupService {

}
