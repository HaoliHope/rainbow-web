package org.rainbow.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().setContentType("application/json");
        event.getResponse().setCharacterEncoding("utf-8");
        Map<String,Object> map  = new HashMap<>();
        map.put("code",0);
        map.put("msg","您已经在另外一台电脑或浏览器登录，被迫下线！");
        event.getResponse().getWriter().println(JSON.toJSONString(map));
    }
}
