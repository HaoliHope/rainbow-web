package org.rainbow.service.impl.system;

import cn.dev33.satoken.stp.StpInterface;
import org.rainbow.mapper.system.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author lihao3
 * @Date 2020/9/30 13:47
 */
@Service
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<Object> getPermissionCodeList(Object loginId, String loginKey) {
        List<String> menus = sysMenuMapper.selectMenuPermsByLoginName((String) loginId);
        return Collections.singletonList(menus);
    }
}
