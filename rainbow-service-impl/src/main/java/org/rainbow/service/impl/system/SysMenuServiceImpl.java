package org.rainbow.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.rainbow.beans.system.SysMenu;
import org.rainbow.beans.exception.BusinessException;
import org.rainbow.beans.exception.code.BaseResponseCode;
import org.rainbow.mapper.system.SysMenuMapper;
import org.rainbow.service.system.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lihao3
 * @Date 2020/11/6 10:25
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> list(SysMenu sysMenu) {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>(sysMenu);
        return sysMenuMapper.selectList(sysMenuQueryWrapper);
    }

    @Override
    public int add(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu);
    }

    @Override
    public int update(SysMenu sysMenu) {
        return sysMenuMapper.updateById(sysMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.lambda().eq(SysMenu::getPid, id);
        int count = sysMenuMapper.selectCount(sysMenuQueryWrapper);
        if (count > 0) {
            throw new BusinessException(BaseResponseCode.MENU_IS_CHINA);
        }
        return sysMenuMapper.deleteById(id);
    }
}
