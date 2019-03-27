package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.TDept;
import cn.umisoft.admin.mapper.TDeptMapper;
import cn.umisoft.admin.repository.TDeptRepository;
import cn.umisoft.admin.service.ITDeptService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class TDeptServiceImpl extends UmiServiceImpl<TDeptMapper, TDeptRepository, TDept> implements ITDeptService {

    @Override
    public List<TDept> queryTree(String...ids) {
        if (ids.length == 0 || (ids.length == 1 && StringUtils.isEmpty(ids[0]))) {
            String[] parentIds = {null, ""};
            ids = this.baseRepository.findAllIdsByParentId(parentIds);
        }
        return this.baseRepository.findTDeptsByIdIn(ids);
    }

    @Override
    public List<TDept> findAllByRoleId(String roleId) {
        return this.baseRepository.findAllByRoleId(roleId);
    }

    @Override
    public List<TDept> findAllByUserId(String userId) {
        return this.baseRepository.findAllByUserId(userId);
    }
}
