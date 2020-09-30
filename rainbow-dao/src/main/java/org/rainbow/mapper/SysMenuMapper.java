package org.rainbow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.rainbow.beans.entity.SysMenu;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据登录名查询他所有的权限标识
     *
     * @param LoginName
     * @return
     */
    List<String> selectMenuPermsByLoginName(@Param("LoginName") String LoginName);

    /**
     * 根据登录名查询他所有菜单权限
     *
     * @param LoginName
     * @return
     */
    List<SysMenu> selectMenuListByLoginName(@Param("LoginName") String LoginName);
}
