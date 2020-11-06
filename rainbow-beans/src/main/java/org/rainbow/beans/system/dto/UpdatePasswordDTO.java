package org.rainbow.beans.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lihao3
 * @Date 2020/11/6 13:31
 */
@Data
@ApiModel("修改密码所用DTO")
public class UpdatePasswordDTO {

    @ApiModelProperty("修改前的密码")
    private String oldPassword;

    @ApiModelProperty("修改后的密码")
    private String newPassword;
}
