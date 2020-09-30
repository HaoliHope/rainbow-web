package org.rainbow.beans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.rainbow.beans.entity.SysMenu;

import java.util.List;

/**
 * @author lihao3
 * @Date 2020/9/29 14:50
 */
@Data
@AllArgsConstructor
public class LoginSuccess {
    /**
     * 登录人用户名
     */
    private String loginName;
    /**
     * 登录人所拥有权限
     */
    private List<SysMenu> menuList;
    /**
     * 登录之后返回的token
     */
    private String token;
}
