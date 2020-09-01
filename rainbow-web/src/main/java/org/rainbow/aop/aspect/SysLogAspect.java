package org.rainbow.aop.aspect;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.rainbow.beans.entity.SysLog;
import org.rainbow.mapper.SysLogMapper;
import org.rainbow.utils.HttpContextUtil;
import org.rainbow.utils.IPUtil;
import org.rainbow.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lihao3
 * @Date 2020/8/27 9:47
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    private IPUtil ipUtils;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private HttpContextUtil httpContextUtils;

    // @Around("@annotation(org.rainbow.aop.annotation.LogAnnotation)")
    // public Object around(ProceedingJoinPoint point) throws Throwable {
    //     long beginTime = System.currentTimeMillis();
    //     //执行方法
    //     Object result = point.proceed();
    //     //执行时长(毫秒)
    //     long time = System.currentTimeMillis() - beginTime;
    //     //保存日志
    //     try {
    //         saveSysLog(point, time);
    //     } catch (Exception e) {
    //         log.error("保存日志出现问题，异常为{}", e.getMessage());
    //     }
    //     return result;
    // }

    /**
     * 添加service层日志AOP
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.rainbow.service.impl.*.* (..))")
    public Object serviceAround(ProceedingJoinPoint point) throws Throwable {
        log.info("===开始执行{}.{}方法===", point.getTarget().getClass(), point.getSignature().getName());
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 输入日志
        if (time > 120000) {
            log.error("===执行结束,耗时{}毫秒===", time);
        } else if (time > 60000) {
            log.warn("===执行结束,耗时{}毫秒===", time);
        } else {
            log.info("===执行结束,耗时{}毫秒===", time);
        }

        return result;
    }

    /**
     * 登陆日志AOP
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.rainbow.controller.AdminController.login(..))")
    public Object controllerAround(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        try {
            saveSysLog(time);
        } catch (Exception e) {
            log.error("保存日志出现问题，异常为{}", e.getMessage());
        }
        return result;
    }

    /**
     * 将日志持久化
     *
     * @param time
     */
    private void saveSysLog(Long time) {
        // 获取http上下文
        HttpServletRequest request = httpContextUtils.getHttpServletRequest();
        // 获取userAgent信息
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //获取操作系统
        OperatingSystem os = userAgent.getOperatingSystem();
        SysLog sysLog = new SysLog();
        sysLog.setLoginIp(ipUtils.getIpAddr(request));
        sysLog.setLoginBrowser(browser.toString());
        sysLog.setLoginOs(os.toString());
        sysLog.setLoginName(jwtTokenUtil.getUserNameFromToken(request.getHeader("Authorization")));
        sysLog.setResponseTime(time);
        sysLogMapper.insert(sysLog);
    }
}
