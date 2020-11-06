package org.rainbow.service.system;

import org.rainbow.beans.system.SysMenu;

import java.util.List;

/**
 * @author lihao3
 * @Date 2020/10/23 20:18
 */
public interface SysMenuService {

    /**
     * 根据实体条件查询list
     *
     * @param sysMenu
     * @return
     */
    List<SysMenu> list(SysMenu sysMenu);

    /**
     * 添加菜单
     *
     * @param sysMenu
     * @return
     */
    int add(SysMenu sysMenu);

    /**
     * 修改菜单信息
     *
     * @param sysMenu
     * @return
     */
    int update(SysMenu sysMenu);

    /**
     * 删除菜单信息
     *
     * @param id
     * @return
     */
    int delete(Long id);
}
