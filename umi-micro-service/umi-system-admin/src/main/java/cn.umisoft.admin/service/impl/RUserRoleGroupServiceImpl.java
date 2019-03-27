package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.RUserRoleGroup;
import cn.umisoft.admin.mapper.RUserRoleGroupMapper;
import cn.umisoft.admin.repository.RUserRoleGroupRepository;
import cn.umisoft.admin.service.IRUserRoleGroupService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色分组，中间表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class RUserRoleGroupServiceImpl extends UmiServiceImpl<RUserRoleGroupMapper, RUserRoleGroupRepository, RUserRoleGroup> implements IRUserRoleGroupService {

}
