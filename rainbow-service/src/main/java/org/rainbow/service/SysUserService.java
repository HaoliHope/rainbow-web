package org.rainbow.service;

import org.rainbow.beans.entity.SysMenu;
import org.rainbow.beans.entity.SysUser;

import java.util.List;

/**
 * 用户业务接口层
 *
 * @author lihao3
 * @Date 2020/8/30 11:47
 */
public interface SysUserService {

    /**
     * 根据用户名查询信息
     *
     * @param loginName
     * @return
     */
    SysUser getUserByLoginName(String loginName);

    /**
     * 用户注册
     *
     * @param sysUser
     * @return
     */
    SysUser userRegister(SysUser sysUser);

    /**
     * 登陆
     *
     * @param loginName
     * @param password
     * @return
     */
    String userLogin(String loginName, String password);

    /**
     * 根据用户ID查询菜单
     *
     * @param userId
     * @return
     */
    List<SysMenu> getMenuList(Long userId);
}
