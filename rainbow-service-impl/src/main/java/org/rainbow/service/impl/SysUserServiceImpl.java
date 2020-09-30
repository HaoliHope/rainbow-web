package org.rainbow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.rainbow.beans.dto.LoginSuccess;
import org.rainbow.beans.entity.SysMenu;
import org.rainbow.beans.entity.SysUser;
import org.rainbow.beans.exception.BusinessException;
import org.rainbow.beans.exception.code.BaseResponseCode;
import org.rainbow.beans.vo.LoginForm;
import org.rainbow.mapper.SysMenuMapper;
import org.rainbow.mapper.SysRoleMapper;
import org.rainbow.mapper.SysUserMapper;
import org.rainbow.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihao3
 * @Date 2020/9/29 14:30
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public SysUser getUserByLoginName(String loginName) {
        return null;
    }

    @Override
    public SysUser userRegister(SysUser sysUser) {
        return null;
    }

    @Override
    public LoginSuccess userLogin(LoginForm loginForm) {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.lambda().eq(SysUser::getLoginName,loginForm.getLoginName()).or()
                .eq(SysUser::getEmail,loginForm.getLoginName());
        SysUser sysUser = sysUserMapper.selectOne(sysUserQueryWrapper);
        if (sysUser==null) {
            throw new BusinessException(BaseResponseCode.LOGIN_ERROR);
        }

        return null;
    }

    @Override
    public List<SysMenu> getMenuList(Long userId) {
        return null;
    }
}
