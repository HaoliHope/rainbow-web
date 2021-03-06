package org.rainbow.beans.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lihao3
 * @Date 2020/8/30 14:13
 */
@ApiModel("登录VO对象")
@Data
public class LoginForm {

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String loginName;

    @ApiModelProperty(value = "密码", required = true)
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码", required = true)
    @NotNull(message = "验证码不能为空")
    private String verKey;

    @ApiModelProperty(value = "验证码", required = true)
    @NotNull(message = "验证码不能为空")
    private String verCode;
}
