package org.rainbow.service.system;

import org.rainbow.beans.system.SysUser;
import org.rainbow.beans.system.dto.LoginForm;
import org.rainbow.beans.system.vo.LoginSuccess;

import javax.servlet.http.HttpServletRequest;

/**
 * 鉴权访问
 *
 * @author lihao3
 * @Date 2020/9/29 15:29
 */
public interface AuthService {
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
     * @param loginForm
     * @return
     */
    LoginSuccess userLogin(LoginForm loginForm, HttpServletRequest request);
}
