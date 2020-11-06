package org.rainbow.beans.system.vo;

import lombok.Data;
import org.rainbow.beans.system.SysMenu;
import org.rainbow.beans.system.SysRole;

import java.util.List;

/**
 * @author lihao3
 * @Date 2020/9/29 14:50
 */
@Data
public class LoginSuccess {

    /**
     * 登录人用户名
     */
    private String loginName;

    /**
     * 请求使用的token
     */
    private String accessToken;

    /**
     * 续签使用的token
     */
    private String refreshToken;

    /**
     * 角色
     */
    private List<SysRole> sysRoleList;

    /**
     * 登陆人的菜单
     */
    private List<SysMenu> sysMenuVOList;

    public LoginSuccess(String loginName, String accessToken, List<SysMenu> sysMenuVOList) {
        this.loginName = loginName;
        this.accessToken = accessToken;
        this.sysMenuVOList = sysMenuVOList;
    }
}
