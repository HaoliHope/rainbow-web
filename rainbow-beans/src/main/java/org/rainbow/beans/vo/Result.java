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

    @ApiModelProperty("时间戳")
    private Long timestamp;

    @ApiModelProperty("提示语")
    private String message;

    @ApiModelProperty("返回数据")
    private Object data;

    public static final Result success(Object data) {
        return new Result(true, System.currentTimeMillis(), "操作成功", data);
    }

    public static final Result error(String message) {
        return new Result(false, System.currentTimeMillis(), message, null);
    }

    public static final Result success(String message) {
        return new Result(true, System.currentTimeMillis(), message, null);
    }
}
