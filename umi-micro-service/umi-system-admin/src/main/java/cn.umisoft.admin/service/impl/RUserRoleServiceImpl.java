package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.RUserRole;
import cn.umisoft.admin.mapper.RUserRoleMapper;
import cn.umisoft.admin.repository.RUserRoleRepository;
import cn.umisoft.admin.service.IRUserRoleService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色中间表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class RUserRoleServiceImpl extends UmiServiceImpl<RUserRoleMapper, RUserRoleRepository, RUserRole> implements IRUserRoleService {

}
