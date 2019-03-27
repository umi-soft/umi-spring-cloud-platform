package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.TSecurity;
import cn.umisoft.admin.mapper.TSecurityMapper;
import cn.umisoft.admin.repository.TSecurityRepository;
import cn.umisoft.admin.service.ITSecurityService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 安全资源定义信息表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class TSecurityServiceImpl extends UmiServiceImpl<TSecurityMapper, TSecurityRepository, TSecurity> implements ITSecurityService {
    @Override
    public List<TSecurity> findAllByMenuId(String menuId) {
        return this.baseRepository.findAllByMenuId(menuId);
    }
}
