package org.rainbow.beans.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.rainbow.beans.system.SysMenu;

import java.util.List;

/**
 * @author lihao3
 * @Date 2020/11/6 15:13
 */
@ApiModel("给角色赋值菜单")
@Data
public class RelatedMenuDTO {

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("菜单list")
    private List<SysMenu> sysMenuList;
}
