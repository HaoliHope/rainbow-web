package org.rainbow.controller;

import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rainbow.beans.entity.SysUser;
import org.rainbow.beans.vo.LoginForm;
import org.rainbow.beans.vo.Result;
import org.rainbow.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lihao3
 * @Date 2020/8/30 14:03
 */
@Api(tags = "权限校验")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody SysUser sysUser) {
        SysUser userRegister = sysUserService.userRegister(sysUser);
        if (userRegister == null) {
            Result.error("注册失败，请稍后再试");
        }
        return Result.success(userRegister);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        boolean checkCaptcha = CaptchaUtil.ver(loginForm.getCaptcha(), request);
        if (checkCaptcha) {
            String token = sysUserService.userLogin(loginForm.getLoginName(), loginForm.getPassword());
            if ("".equals(token)) {
                return Result.error("账号或者密码错误");
            } else {
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("token", token);
                tokenMap.put("tokenHead", tokenHead);
                return Result.success(tokenMap);
            }
        } else {
            CaptchaUtil.clear(request);
            return Result.error("验证码输入错误");
        }

    }

    @ApiOperation("验证码生成")
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }
}
