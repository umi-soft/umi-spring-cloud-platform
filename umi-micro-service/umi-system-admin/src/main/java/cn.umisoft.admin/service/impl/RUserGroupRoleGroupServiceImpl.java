package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.RUserGroupRoleGroup;
import cn.umisoft.admin.mapper.RUserGroupRoleGroupMapper;
import cn.umisoft.admin.repository.RUserGroupRoleGroupRepository;
import cn.umisoft.admin.service.IRUserGroupRoleGroupService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户分组-角色分组，中间表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class RUserGroupRoleGroupServiceImpl extends UmiServiceImpl<RUserGroupRoleGroupMapper, RUserGroupRoleGroupRepository, RUserGroupRoleGroup> implements IRUserGroupRoleGroupService {

}
