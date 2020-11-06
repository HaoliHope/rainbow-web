package org.rainbow.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.rainbow.beans.system.SysRole;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据登录名查询他所拥有的角色列表
     *
     * @param loginName
     * @return
     */
    List<String> selectRoleListByLoginName(@Param("loginName") String loginName);
}
