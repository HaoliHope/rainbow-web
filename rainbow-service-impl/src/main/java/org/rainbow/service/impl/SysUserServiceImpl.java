package org.rainbow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.rainbow.beans.entity.SysMenu;
import org.rainbow.beans.entity.SysUser;
import org.rainbow.mapper.SysMenuMapper;
import org.rainbow.mapper.SysUserMapper;
import org.rainbow.service.SysUserService;
import org.rainbow.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihao3
 * @Date 2020/8/30 11:52
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public SysUser getUserByLoginName(String loginName) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getLoginName, loginName);
        return sysUserMapper.selectOne(wrapper);
    }

    @Override
    public SysUser userRegister(SysUser sysUser) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getLoginName, sysUser.getLoginName());
        // 查询是否有重复的用户名
        Integer count = sysUserMapper.selectCount(wrapper);
        if (count > 1) {
            throw new RuntimeException("此用户名已经注册！");
        } else {
            // 将密码加密
            String encodePassword = passwordEncoder.encode(sysUser.getPassword());
            sysUser.setPassword(encodePassword);
            sysUserMapper.insert(sysUser);
            return sysUser;
        }
    }

    @Override
    public String userLogin(String loginName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginName);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("账号或密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public List<SysMenu> getMenuList(Long userId) {
        return sysMenuMapper.selectMenuByUserId(userId);
    }
}
