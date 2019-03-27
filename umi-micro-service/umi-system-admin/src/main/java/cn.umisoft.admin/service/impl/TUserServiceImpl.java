package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.TUser;
import cn.umisoft.admin.mapper.TUserMapper;
import cn.umisoft.admin.repository.TUserRepository;
import cn.umisoft.admin.service.ITUserService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class TUserServiceImpl extends UmiServiceImpl<TUserMapper, TUserRepository, TUser> implements ITUserService {
    @Override
    public Boolean updatePasswordById(String id, String password) {
        TUser user = new TUser();
        user.setId(id);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        return SqlHelper.retBool(this.baseMapper.updateById(user));
    }

    @Override
    public TUser login(String loginName, String password) {
        QueryWrapper<TUser> qw = new QueryWrapper<TUser>();
        qw.eq("LOGIN_NAME", loginName).eq("PASSWORD", DigestUtils.md5DigestAsHex(password.getBytes()));
        return this.baseMapper.selectOne(qw);
    }

    @Override
    public List<TUser> findAllByLoginName(String loginName) {
        return this.baseRepository.findAllByLoginNameIgnoreCase(loginName);
    }

    @Override
    public List<TUser> findAllByDeptId(String deptId) {
        return this.baseRepository.findAllByDeptId(deptId);
    }

    @Override
    public List<TUser> findAllByRoleId(String roleId) {
        return this.baseRepository.findAllByRoleId(roleId);
    }

    @Override
    public List<TUser> findAllByUserGroupId(String userGroupId) {
        return this.baseRepository.findAllByUserGroupId(userGroupId);
    }

    @Override
    public List<TUser> findAllByRoleGroupId(String roleGroupId) {
        return this.baseRepository.findAllByRoleGroupId(roleGroupId);
    }
}
