package org.rainbow.beans.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lihao3
 */
@Data
@AllArgsConstructor
@ApiOperation("普通返回格式")
public class Result {

    @ApiModelProperty("是否成功")
    private boolean success;

    @ApiModelProperty("状态码")
    private String code;

    @ApiModelProperty("时间戳")
    private Long timestamp;

    @ApiModelProperty("提示语")
    private String message;

    @ApiModelProperty("返回数据")
    private Object data;

    /**
     * 需要返回数据的成功
     *
     * @param data
     * @return
     */
    public static final Result success(String message, Object data) {
        return new Result(true, "0", System.currentTimeMillis(), message, data);
    }

    /**
     * 不需要返回数据的成功
     *
     * @return
     */
    public static final Result success() {
        return new Result(true, "0", System.currentTimeMillis(), "操作成功", null);
    }

    public static final Result error(String code, String message) {
        return new Result(false, code, System.currentTimeMillis(), message, null);
    }

}
