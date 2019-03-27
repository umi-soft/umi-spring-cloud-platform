package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.TGroup;
import cn.umisoft.admin.mapper.TGroupMapper;
import cn.umisoft.admin.repository.TGroupRepository;
import cn.umisoft.admin.service.ITGroupService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分组信息表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class TGroupServiceImpl extends UmiServiceImpl<TGroupMapper, TGroupRepository, TGroup> implements ITGroupService {
    @Override
    public List<TGroup> findAllRoleGroupByRoleId(String roleId) {
        return this.baseRepository.findAllRoleGroupByRoleId(roleId);
    }

    @Override
    public List<TGroup> findAllUserGroupByUserId(String userId) {
        return this.baseRepository.findAllUserGroupByUserId(userId);
    }

    @Override
    public List<TGroup> findAllRoleGroupByUserId(String userId) {
        return this.baseRepository.findAllRoleGroupByUserId(userId);
    }

    @Override
    public List<TGroup> findAllRoleGroupByUserGroupId(String userGroupId) {
        return this.baseRepository.findAllRoleGroupByUserGroupId(userGroupId);
    }

    @Override
    public List<TGroup> findAllUserGroupByRoleGroupId(String roleGroupId) {
        return this.baseRepository.findAllUserGroupByRoleGroupId(roleGroupId);
    }
}
