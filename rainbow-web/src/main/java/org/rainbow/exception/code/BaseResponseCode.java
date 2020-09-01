package org.rainbow.exception.code;

/**
 * @author lihao3
 * @Date 2020/9/1 10:08
 */
public enum BaseResponseCode implements ResponseCodeInterface {
    ;

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
