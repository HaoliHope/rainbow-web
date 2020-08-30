package org.rainbow.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 *
 * @author lihao3
 * @Date 2020/8/30 13:53
 */
@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", 403);
        map.put("msg", "请先登录");
        response.getWriter().println(JSON.toJSONString(map));
    }
}
