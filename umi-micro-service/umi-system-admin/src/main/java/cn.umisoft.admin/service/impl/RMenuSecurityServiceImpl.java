package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.RMenuSecurity;
import cn.umisoft.admin.mapper.RMenuSecurityMapper;
import cn.umisoft.admin.repository.RMenuSecurityRepository;
import cn.umisoft.admin.service.IRMenuSecurityService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单-安全资源中间表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class RMenuSecurityServiceImpl extends UmiServiceImpl<RMenuSecurityMapper, RMenuSecurityRepository, RMenuSecurity> implements IRMenuSecurityService {

}
