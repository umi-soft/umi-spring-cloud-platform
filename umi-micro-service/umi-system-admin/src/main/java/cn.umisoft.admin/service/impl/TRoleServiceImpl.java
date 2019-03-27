package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.TRole;
import cn.umisoft.admin.mapper.TRoleMapper;
import cn.umisoft.admin.repository.TRoleRepository;
import cn.umisoft.admin.service.ITRoleService;
import cn.umisoft.util.context.UmiUserContextHolder;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class TRoleServiceImpl extends UmiServiceImpl<TRoleMapper, TRoleRepository, TRole> implements ITRoleService {
    @Override
    public HashSet<String> findAllByCurrentUserId() {
        String id = UmiUserContextHolder.getContext();
        HashSet<String> set = new HashSet<String>();
        List<String> list = this.baseRepository.findAllFromUserDept(id);
        set.addAll(list);
        list = this.baseRepository.findAllFromUserGroup(id);
        set.addAll(list);
        list = this.baseRepository.findAllFromUserRole(id);
        set.addAll(list);
        list = this.baseRepository.findAllFromUserRoleGroup(id);
        set.addAll(list);
        return set;
    }

    @Override
    public List<TRole> findAllByDeptId(String deptId) {
        return this.baseRepository.findAllByDeptId(deptId);
    }
    @Override
    public List<TRole> findAllByUserId(String userId) {
        return this.baseRepository.findAllByUserId(userId);
    }

    @Override
    public List<TRole> findAllByMenuId(String menuId) {
        return this.baseRepository.findAllByMenuId(menuId);
    }

    @Override
    public List<TRole> findAllByRoleGroupId(String roleGroupId) {
        return this.baseRepository.findAllByRoleGroupId(roleGroupId);
    }
}
