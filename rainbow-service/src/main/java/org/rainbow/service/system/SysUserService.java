package org.rainbow.service.system;

import org.rainbow.beans.system.SysMenu;
import org.rainbow.beans.system.SysUser;
import org.rainbow.beans.system.dto.UpdatePasswordDTO;

import java.util.List;

/**
 * 用户业务接口层
 *
 * @author lihao3
 * @Date 2020/8/30 11:47
 */
public interface SysUserService {

    /**
     * 根据用户名查询信息
     *
     * @param loginName
     * @return
     */
    SysUser getUserByLoginName(String loginName);

    /**
     * 根据用户ID查询菜单
     *
     * @param userId
     * @return
     */
    List<SysMenu> getMenuList(Long userId);

    /**
     * 修改密码
     *
     * @param updatePasswordDTO
     * @return
     */
    int updatePassword(UpdatePasswordDTO updatePasswordDTO);
}
