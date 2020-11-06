package org.rainbow.utils;

import cn.dev33.satoken.stp.StpUtil;

/**
 * 根据token获取用户信息
 *
 * @author lihao3
 * @Date 2020/11/6 15:28
 */
public class GetUserInfo {

    public static String getLoginId() {
        String token = getToken();
        return (String) StpUtil.getLoginIdByToken(token);
    }

    /**
     * 获取http中的token值
     *
     * @return
     */
    private static String getToken() {
        return HttpContextUtils.getHttpServletRequest().getHeader("Authorization");
    }
}
