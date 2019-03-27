package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.RUserGroup;
import cn.umisoft.admin.mapper.RUserGroupMapper;
import cn.umisoft.admin.repository.RUserGroupRepository;
import cn.umisoft.admin.service.IRUserGroupService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-分组中间表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class RUserGroupServiceImpl extends UmiServiceImpl<RUserGroupMapper, RUserGroupRepository, RUserGroup> implements IRUserGroupService {

}
