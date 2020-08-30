package org.rainbow.beans.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "org-rainbow-beans-entity-SysLog")
@Data
public class SysLog implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;

    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址")
    private String loginIp;

    /**
     * 登陆浏览器
     */
    @ApiModelProperty(value = "登陆浏览器")
    private String loginBrowser;

    /**
     * 操作系统
     */
    @ApiModelProperty(value = "login_os")
    private String loginOs;

    /**
     * 登陆时间
     */
    @ApiModelProperty(value = "登陆时间")
    private Date loginTime;

    /**
     * 响应时间
     */
    @ApiModelProperty(value = "响应时间")
    private Long responseTime;

    private static final long serialVersionUID = 1L;
}

