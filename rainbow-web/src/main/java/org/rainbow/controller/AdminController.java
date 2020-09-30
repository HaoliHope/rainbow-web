package org.rainbow.controller;

import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rainbow.beans.dto.LoginSuccess;
import org.rainbow.beans.vo.LoginForm;
import org.rainbow.beans.vo.Result;
import org.rainbow.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lihao3
 * @Date 2020/8/30 14:03
 */
@Api(tags = "AdminController", description = "权限校验")
@RestController
@RequestMapping("/auth")
public class AdminController {

    @Autowired
    AuthService authService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @ResponseBody
    @GetMapping("/captcha")
    public Result captcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30分钟
        stringRedisTemplate.opsForValue().set(key, verCode, 30, TimeUnit.HOURS);
        // 将key和base64返回给前端
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("image", specCaptcha.toBase64());
        return Result.success("获取验证码成功", map);
    }

    @ApiOperation("登陆接口")
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        LoginSuccess loginSuccess = authService.userLogin(loginForm, request);
        return Result.success("登陆成功!", loginSuccess);
    }
}