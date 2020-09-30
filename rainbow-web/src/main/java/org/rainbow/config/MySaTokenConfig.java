package org.rainbow.config;

import cn.dev33.satoken.annotation.SaCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lihao3
 * @Date 2020/9/30 14:17
 */
@Configuration
public class MySaTokenConfig implements WebMvcConfigurer {

    /**
     * 注册sa-token的拦截器，打开注解式鉴权功能
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 全局拦截器
        registry.addInterceptor(new SaCheckInterceptor()).addPathPatterns("/**");
    }
}
