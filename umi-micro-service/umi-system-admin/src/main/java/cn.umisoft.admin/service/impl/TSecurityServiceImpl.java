package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.dto.SecurityRoleDTO;
import cn.umisoft.admin.entity.TSecurity;
import cn.umisoft.admin.mapper.TSecurityMapper;
import cn.umisoft.admin.repository.TSecurityRepository;
import cn.umisoft.admin.service.ITSecurityService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public Map<String, Set<String>> findAllSecurityRoleMapping() {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        List<SecurityRoleDTO> list = this.baseMapper.selectAllSecurityRoleDTO();
        list.forEach(item -> {
            if (map.get(item.getServiceId() + item.getSecurityDef()) == null) {
                map.put(item.getServiceId() + item.getSecurityDef(), new HashSet<String>());
            }
            map.get(item.getServiceId() + item.getSecurityDef()).addAll(item.getRoles());
        });
        return map;
    }
}
