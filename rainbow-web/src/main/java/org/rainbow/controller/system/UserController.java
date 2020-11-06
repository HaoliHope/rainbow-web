package org.rainbow.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rainbow.beans.system.dto.UpdatePasswordDTO;
import org.rainbow.beans.vo.Result;
import org.rainbow.service.system.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihao3
 * @Date 2020/11/6 11:00
 */
@Api(tags = "用户接口", description = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    SysUserService sysUserService;

    @PutMapping("/update/password")
    @ApiOperation("修改密码")
    public Result updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        sysUserService.updatePassword(updatePasswordDTO);
        return Result.success();
    }
}
