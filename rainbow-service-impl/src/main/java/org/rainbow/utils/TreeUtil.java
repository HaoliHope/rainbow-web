package org.rainbow.utils;

import org.rainbow.beans.system.SysMenu;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 树级菜单工具类
 *
 * @author lihao3
 * @Date 2020/9/30 10:30
 */
@Component
public class TreeUtil {

    /**
     * 创建树结构
     *
     * @param treeNodes
     * @return
     */
    public List<SysMenu> RecursiveNodes(List<SysMenu> treeNodes) {
        List<SysMenu> sysMenus = new ArrayList<>();
        Iterator<SysMenu> it = treeNodes.iterator();
        while (it.hasNext()) {
            SysMenu next = it.next();
            // 获取所有顶级菜单
            if (next.getPid() == 0) {
                sysMenus.add(next);
                it.remove();
            }
        }
        for (SysMenu sysMenu : sysMenus) {
            sysMenu.setChildren(getChild(sysMenu.getId(), treeNodes));
        }
        return sysMenus;
    }

    /**
     * 递归获取下级菜单
     *
     * @param pid      上级Id
     * @param sysMenus 所有菜单
     * @return
     */
    private List<SysMenu> getChild(Long pid, List<SysMenu> sysMenus) {
        //子菜单列表
        List<SysMenu> childList = new ArrayList<>();
        Iterator<SysMenu> it = sysMenus.iterator();
        while (it.hasNext()) {
            SysMenu next = it.next();
            if (pid.equals(next.getPid())) {
                childList.add(next);
                it.remove();
            }
        }
        //遍历 获取子菜单的子菜单
        for (SysMenu sysMenu : childList) {
            List<SysMenu> child = getChild(sysMenu.getId(), sysMenus);
            sysMenu.setChildren(child);
        }
        //递归出口  childList长度为0
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        return childList;
    }

}
