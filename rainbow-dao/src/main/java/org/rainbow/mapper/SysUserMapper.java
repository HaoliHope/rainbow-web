package org.rainbow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.rainbow.beans.entity.SysUser;

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
     * 根据用户角色查询用户权限
     *
     * @param roleCodes
     * @return
     */
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);

    Set<String> findPermissionsByLoginName(@Param("loginName") String loginName);
}
