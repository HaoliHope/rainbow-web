package org.rainbow.beans.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiOperation("分页返回数据格式")
public class PageResult {

    @ApiModelProperty("是否成功")
    private boolean success;

    @ApiModelProperty("时间戳")
    private Long timestamp;

    @ApiModelProperty("提示语")
    private String message;

    @ApiModelProperty("返回数据")
    private Object data;

    @ApiModelProperty("总条数")
    private int totalCount;

    public static final PageResult success(Object data,int totalCount) {
        return new PageResult(true, System.currentTimeMillis(), "操作成功", data,totalCount);
    }
}
