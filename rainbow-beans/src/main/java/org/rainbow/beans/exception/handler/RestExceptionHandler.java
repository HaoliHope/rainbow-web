package org.rainbow.beans.exception.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.rainbow.beans.exception.BusinessException;
import org.rainbow.beans.exception.code.BaseResponseCode;
import org.rainbow.beans.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lihao3
 * @Date 2020/9/4 13:48
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public Result notLoginException() {
        return Result.error(BaseResponseCode.NOT_LOGIN);
    }

    @ExceptionHandler(NotPermissionException.class)
    public Result NotPermissionException() {
        return Result.error(BaseResponseCode.NOT_PERMISSION);
    }

    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(String messageCode, String detailMessage) {
        log.error("业务异常:{}", detailMessage);
        return Result.error(messageCode, detailMessage);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常:{}", e.getMessage());
        return Result.error(BaseResponseCode.ERROR);
    }

}
