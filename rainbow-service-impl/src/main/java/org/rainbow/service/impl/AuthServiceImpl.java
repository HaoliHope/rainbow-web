package org.rainbow.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jasypt.encryption.StringEncryptor;
import org.rainbow.beans.dto.LoginSuccess;
import org.rainbow.beans.entity.SysMenu;
import org.rainbow.beans.entity.SysUser;
import org.rainbow.beans.exception.BusinessException;
import org.rainbow.beans.exception.code.BaseResponseCode;
import org.rainbow.beans.vo.LoginForm;
import org.rainbow.mapper.SysMenuMapper;
import org.rainbow.mapper.SysRoleMapper;
import org.rainbow.mapper.SysUserMapper;
import org.rainbow.service.AuthService;
import org.rainbow.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lihao3
 * @Date 2020/9/29 15:30
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    TreeUtil treeUtil;
    @Autowired
    StringEncryptor encode;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public SysUser userRegister(SysUser sysUser) {
        return null;
    }

    @Override
    public LoginSuccess userLogin(LoginForm loginForm, HttpServletRequest request) {
        // 判断验证码是否真确
        String verCode = stringRedisTemplate.opsForValue().get(loginForm.getVerKey());
        if (StringUtils.isEmpty(verCode)) {
            throw new BusinessException(BaseResponseCode.VERCODE_TIMEOUT);
        } else if (!verCode.equals(loginForm.getVerCode())) {
            throw new BusinessException(BaseResponseCode.VERCODE_ERROR);
        }
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.lambda().eq(SysUser::getLoginName, loginForm.getLoginName()).or()
                .eq(SysUser::getEmail, loginForm.getLoginName());
        // 根据用户名查询是否该用户
        SysUser sysUser = sysUserMapper.selectOne(sysUserQueryWrapper);
        // 如果没有则直接抛异常
        if (sysUser == null) {
            throw new BusinessException(BaseResponseCode.LOGIN_ERROR);
        } else {
            if (!encode.decrypt(loginForm.getPassword()).equals(sysUser.getPassword())) {
                throw new BusinessException(BaseResponseCode.LOGIN_ERROR);
            } else {
                // 查询他的所有菜单
                List<SysMenu> menuList = sysMenuMapper.selectMenuListByLoginName(loginForm.getLoginName());
                // 将其转换为树形结构
                List<SysMenu> sysMenuList = treeUtil.RecursiveNodes(menuList);
                // 执行登陆方法
                StpUtil.setLoginId(loginForm.getLoginName());
                return new LoginSuccess(loginForm.getLoginName(), sysMenuList, StpUtil.getTokenValue());
            }
        }
    }
}
