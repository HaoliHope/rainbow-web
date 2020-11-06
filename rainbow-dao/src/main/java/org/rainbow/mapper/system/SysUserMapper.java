package org.rainbow.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.rainbow.beans.system.SysUser;

import java.util.List;
import java.util.Set;

/**
 * @author lihao3
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    /**
     * 根据登录名查询用户角色列表
     *
     * @param loginName
     * @return
     */
    List<String> findRoleByLoginName(@Param("loginName") String loginName);

    /**
     * 根据用户名查询他所拥有的菜单代码
     *
     * @param loginName
     * @return
     */
    Set<String> findPermissionsByLoginName(@Param("loginName") String loginName);

    /**
     * 修改用户表的最近登录时间和IP
     *
     * @param sysUser
     * @return
     */
    int logLogin(SysUser sysUser);
}
