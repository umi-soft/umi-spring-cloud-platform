package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.RMenuRole;
import cn.umisoft.admin.mapper.RMenuRoleMapper;
import cn.umisoft.admin.repository.RMenuRoleRepository;
import cn.umisoft.admin.service.IRMenuRoleService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单-角色中间表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class RMenuRoleServiceImpl extends UmiServiceImpl<RMenuRoleMapper, RMenuRoleRepository, RMenuRole> implements IRMenuRoleService {

}
