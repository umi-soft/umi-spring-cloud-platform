package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.RUserDept;
import cn.umisoft.admin.mapper.RUserDeptMapper;
import cn.umisoft.admin.repository.RUserDeptRepository;
import cn.umisoft.admin.service.IRUserDeptService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-部门中间表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class RUserDeptServiceImpl extends UmiServiceImpl<RUserDeptMapper, RUserDeptRepository, RUserDept> implements IRUserDeptService {

}
