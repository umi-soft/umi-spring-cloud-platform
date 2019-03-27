package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.RMenuRole;
import cn.umisoft.admin.entity.TMenu;
import cn.umisoft.admin.mapper.TMenuMapper;
import cn.umisoft.admin.repository.RMenuRoleRepository;
import cn.umisoft.admin.repository.TMenuRepository;
import cn.umisoft.admin.service.ITMenuService;
import cn.umisoft.util.exceptions.DaoHandlerGlobalException;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 前端路由菜单信息表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class TMenuServiceImpl extends UmiServiceImpl<TMenuMapper, TMenuRepository, TMenu> implements ITMenuService {

    @Autowired
    protected RMenuRoleRepository menuRoleRepository;

    @Override
    public List<TMenu> findAllByRoleId(String roleId) {
        return this.baseRepository.findAllByRoleId(roleId);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public Map<String, List<Map<String, String>>> syncMenus(List<TMenu> menus) {

        Map<String, List<Map<String, String>>> result = new HashMap<String, List<Map<String, String>>>();
        result.put("insert", new ArrayList<Map<String, String>>());
        result.put("update", new ArrayList<Map<String, String>>());
        result.put("delete", new ArrayList<Map<String, String>>());

        List<TMenu> history = this.baseMapper.selectList(new QueryWrapper<TMenu>());
        Map<String, String> ids = new HashMap<String, String>();
        for (TMenu menu : history) {
            ids.put(menu.getId(), menu.getName());
        }
        for (TMenu menu : menus) {
            Map<String, String> temp = new HashMap<String, String>();
            if (ids.get(menu.getId()) == null) {
                // 全新的路由
                this.baseMapper.insert(menu);
                temp.put(menu.getId(), menu.getName());
                result.get("insert").add(temp);
            } else {
                // 已存在的
                this.baseMapper.updateById(menu);
                temp.put(menu.getId(), menu.getName());
                result.get("update").add(temp);
                // 操作完成，移除，最终剩下的则为前端路由移除的
                ids.remove(menu.getId());
            }
        }
        // 剩下的部分为前端路由移除了，后端没有移除的藏数据，逻辑删除即可
        for (String id : ids.keySet()) {
            Map<String, String> temp = new HashMap<String, String>();
            try {
                TMenu menu = getEntityClass().newInstance();
                menu.setId((String)id);
                this.baseMapper.deleteByIdWithFill(menu);
                temp.put(id, ids.get(id));
                result.get("delete").add(temp);
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new DaoHandlerGlobalException("删除历史路由记录时发生错误");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new DaoHandlerGlobalException("删除历史路由记录时发生错误");
            }
        }
        return result;
    }

    @Override
    public Map<String, Set<String>> findAllRouterRoles() {
        Map<String, Set<String>> result = new HashMap<String, Set<String>>();
        List<RMenuRole> menuRoles = this.menuRoleRepository.findAll();
        for (RMenuRole menuRole : menuRoles) {
            Set<String> roles = result.get(menuRole.getMenuId());
            if (roles == null) {
                roles = new HashSet<String>();
            }
            roles.add(menuRole.getRoleId());
            result.put(menuRole.getMenuId(), roles);
        }
        return result;
    }
}
