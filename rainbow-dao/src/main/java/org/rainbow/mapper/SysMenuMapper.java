package org.rainbow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.rainbow.beans.entity.SysMenu;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户ID查询他的权限菜单
     *
     * @param userId
     * @return
     */
    List<SysMenu> selectMenuByUserId(Long userId);
}
