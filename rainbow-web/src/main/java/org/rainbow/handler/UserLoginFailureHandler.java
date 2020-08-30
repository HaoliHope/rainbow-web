package org.rainbow.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 登录失败处理类
 *
 * @author lihao3
 */
@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", false);
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (e instanceof UsernameNotFoundException) {
            log.info("【登录失败】" + e.getMessage());
            map.put("msg", "用户名不存在");
            httpServletResponse.getWriter().println(JSON.toJSONString(map));
        }
        if (e instanceof LockedException) {
            log.info("【登录失败】" + e.getMessage());
            map.put("msg", "用户被冻结");
            httpServletResponse.getWriter().println(JSON.toJSONString(map));
        }
        if (e instanceof BadCredentialsException) {
            log.info("【登录失败】" + e.getMessage());
            map.put("msg", "用户名密码不正确");
            httpServletResponse.getWriter().println(JSON.toJSONString(map));
        }else{
            map.put("msg", "登录失败");
            httpServletResponse.getWriter().println(JSON.toJSONString(map));
        }
    }
}
