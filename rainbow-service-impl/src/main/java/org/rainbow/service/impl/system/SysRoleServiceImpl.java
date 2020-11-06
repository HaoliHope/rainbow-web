package org.rainbow.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.rainbow.beans.exception.BusinessException;
import org.rainbow.beans.system.SysRole;
import org.rainbow.beans.system.SysRoleMenu;
import org.rainbow.beans.system.dto.RelatedMenuDTO;
import org.rainbow.mapper.system.SysRoleMapper;
import org.rainbow.mapper.system.SysRoleMenuMapper;
import org.rainbow.service.system.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lihao3
 * @Date 2020/11/6 10:46
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Page<SysRole> list(SysRole sysRole, Integer pageSize, Integer pageNum) {
        Page<SysRole> sysRolePage = new Page<>(pageSize, pageNum);
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>(sysRole);
        Page<SysRole> rolePage = sysRoleMapper.selectPage(sysRolePage, sysRoleQueryWrapper);
        return rolePage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(SysRole sysRole) {
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.lambda()
                .eq(SysRole::getRoleName, sysRole.getRoleName())
                .or()
                .eq(SysRole::getRoleCode, sysRole.getRoleCode());
        int count = sysRoleMapper.selectCount(sysRoleQueryWrapper);
        if (count > 0) {
            throw new BusinessException("42000", "");
        }
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public int update(SysRole sysRole) {
        return sysRoleMapper.updateById(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        // 删除中间表
        QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();
        sysRoleMenuQueryWrapper.lambda().eq(SysRoleMenu::getMenuId, id);
        sysRoleMenuMapper.delete(sysRoleMenuQueryWrapper);
        return sysRoleMapper.deleteById(id);
    }

    @Override
    public void relatedMenu(RelatedMenuDTO relatedMenuDTO) {
        // 删除原来的权限
        QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();
        sysRoleMenuQueryWrapper.lambda()
                .eq(SysRoleMenu::getRoleId, relatedMenuDTO.getRoleId());
        sysRoleMenuMapper.delete(sysRoleMenuQueryWrapper);
        // 加上现在的权限
        relatedMenuDTO.getSysMenuList().forEach(item -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(relatedMenuDTO.getRoleId());
            sysRoleMenu.setMenuId(item.getId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        });
    }
}
