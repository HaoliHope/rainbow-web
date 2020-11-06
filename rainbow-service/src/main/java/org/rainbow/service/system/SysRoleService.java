package org.rainbow.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.rainbow.beans.system.SysRole;
import org.rainbow.beans.system.dto.RelatedMenuDTO;

/**
 * @author lihao3
 * @Date 2020/10/23 20:18
 */
public interface SysRoleService {

    /**
     * @param sysRole
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<SysRole> list(SysRole sysRole, Integer pageSize, Integer pageNum);

    /**
     * 添加菜单
     *
     * @param sysRole
     * @return
     */
    int add(SysRole sysRole);

    /**
     * 修改菜单信息
     *
     * @param sysRole
     * @return
     */
    int update(SysRole sysRole);

    /**
     * 删除菜单信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改角色的菜单权限
     *
     * @param relatedMenuDTO
     * @return
     */
    void relatedMenu(RelatedMenuDTO relatedMenuDTO);
}
