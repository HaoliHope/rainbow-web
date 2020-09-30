package org.rainbow;

import cn.dev33.satoken.SaTokenManager;
import cn.dev33.satoken.spring.SaTokenSetup;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SaTokenSetup
@SpringBootApplication
@MapperScan("org.rainbow.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println(SaTokenManager.getConfig());
    }
}
