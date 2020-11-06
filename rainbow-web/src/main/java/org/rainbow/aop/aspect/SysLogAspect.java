package org.rainbow.aop.aspect;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.rainbow.beans.system.SysUser;
import org.rainbow.mapper.system.SysUserMapper;
import org.rainbow.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lihao3
 * @Date 2020/8/27 9:47
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 记录web层请求日志
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.rainbow.controller..*(..))")
    public Object requestServer(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("===============================请求开始===============================");
        log.info("请求IP                 : {}", IPUtil.getIpAddr(request));
        log.info("请求地址                : {}", request.getRequestURL().toString());
        log.info("请求方式        : {}", request.getMethod());
        log.info("请求类       : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        log.info("请求参数     : {}", getRequestParams(joinPoint));
        log.info("返回值              : {}", result);
        log.info("花费时间            : {} ms", System.currentTimeMillis() - start);
        return result;
    }

    @After("execution(* org.rainbow.controller..*(..))")
    public void doAfter() {
        log.info("===============================请求结束===============================");
    }

    /**
     * 记录登陆请求
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.rainbow.controller.system.AuthController.login())")
    public Object loginServer(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        SysUser sysUser = new SysUser();
        sysUser.setLastLoginIp(IPUtil.getIpAddr(request));
        sysUser.setLastLoginTime(new Date());
        sysUser.setLoginName((String) StpUtil.getLoginId());
        return sysUserMapper.logLogin(sysUser);
    }

    /**
     * 获取入参
     *
     * @param proceedingJoinPoint
     * @return
     */
    private Map<String, Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> requestParams = new HashMap<>(16);
        //参数名
        String[] paramNames =
                ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                //获取文件名
                value = file.getOriginalFilename();
            }
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }
}
