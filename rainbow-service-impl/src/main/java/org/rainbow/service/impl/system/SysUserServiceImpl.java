package org.rainbow.service.impl.system;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.rainbow.beans.exception.BusinessException;
import org.rainbow.beans.system.SysMenu;
import org.rainbow.beans.system.SysUser;
import org.rainbow.beans.system.dto.UpdatePasswordDTO;
import org.rainbow.mapper.system.SysMenuMapper;
import org.rainbow.mapper.system.SysRoleMapper;
import org.rainbow.mapper.system.SysUserMapper;
import org.rainbow.service.system.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<SysMenu> getMenuList(Long userId) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        // 获取当前登录人的用户名
        String loginId = (String) StpUtil.getLoginId();
        // 查询原来的密码
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.lambda().eq(SysUser::getLoginName, loginId);
        SysUser sysUser = sysUserMapper.selectOne(sysUserQueryWrapper);
        // 查询旧密码是否输入错误
        if (BCrypt.checkpw(updatePasswordDTO.getOldPassword(), sysUser.getLoginPassword())) {
            throw new BusinessException("43000", "原密码输入错误!");
        }
        if (updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getOldPassword())) {
            throw new BusinessException("43001", "新密码不能旧密码相同!");
        }
        // 修改密码
        SysUser user = new SysUser();
        user.setId(sysUser.getId());
        user.setLoginPassword(BCrypt.hashpw(updatePasswordDTO.getNewPassword()));
        return sysUserMapper.updateById(user);
    }
}
