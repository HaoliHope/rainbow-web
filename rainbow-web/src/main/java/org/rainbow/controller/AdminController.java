package org.rainbow.controller;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rainbow.beans.entity.SysUser;
import org.rainbow.beans.vo.LoginForm;
import org.rainbow.beans.vo.Result;
import org.rainbow.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihao3
 * @Date 2020/8/30 14:03
 */
@Api(tags = "AdminController", description = "权限校验")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    @Lazy
    private CaptchaService captchaService;
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody SysUser sysUser) {
        SysUser userRegister = sysUserService.userRegister(sysUser);
        if (userRegister == null) {
            Result.error("500", "注册失败，请稍后再试");
        }
        return Result.success("注册成功", userRegister);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, @RequestBody CaptchaVO captchaVO) {
        ResponseModel response = captchaService.verification(captchaVO);
        if (response.isSuccess()) {
            String token = sysUserService.userLogin(loginForm.getLoginName(), loginForm.getPassword());
            if ("".equals(token)) {
                return Result.error("502", "账号或者密码错误");
            } else {
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("token", token);
                tokenMap.put("tokenHead", tokenHead);
                return Result.success("登陆成功", tokenMap);
            }
        } else {
            return Result.error(response.getRepCode(), response.getRepMsg());
        }
    }
}