package org.rainbow.service.impl;

import com.anji.captcha.service.CaptchaCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author lihao3
 * @Date 2020/9/1 10:54
 */
@Service
public class CaptchaCacheServiceRedisImpl implements CaptchaCacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void set(String key, String value, long expiresInSeconds) {
        stringRedisTemplate.opsForValue().set(key, value, expiresInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String type() {
        return "redis";
    }
}
