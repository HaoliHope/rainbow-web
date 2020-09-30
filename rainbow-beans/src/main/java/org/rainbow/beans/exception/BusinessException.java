package org.rainbow.beans.exception;

import lombok.Data;
import org.rainbow.beans.exception.code.BaseResponseCode;

/**
 * @author lihao3
 * @Date 2020/9/4 13:43
 */
@Data
public class BusinessException extends RuntimeException {
    /**
     * 异常编号
     */
    private String messageCode;

    /**
     * 对messageCode 异常信息进行补充说明
     */
    private String detailMessage;




    public BusinessException(BaseResponseCode baseResponseCode) {
        super();
        this.messageCode = baseResponseCode.getCode();
        this.detailMessage = baseResponseCode.getMsg();
    }
}
