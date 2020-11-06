package org.rainbow.beans.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lihao3
 * @Date 2020/9/1 10:08
 */
@Getter
@AllArgsConstructor
public enum BaseResponseCode {
    SUCCESS("20000", "操作成功"),
    UPDATE_SUCCESS("20001", "修改成功"),
    SELECT_SUCCESS("20002", "查询成功"),
    DELETE_SUCCESS("20003", "删除成功"),
    SAVE_SUCCESS("20004", "添加成功"),
    ERROR("50000", "系统繁忙，请稍候再试!"),
    LOGIN_ERROR("40000", "账号或者密码输入错误!"),
    NOT_LOGIN("40001", "请先登录!"),
    NOT_PERMISSION("40002", "暂未拥有访问该路径的权限!"),
    VERCODE_ERROR("40003", "验证码输入错误"),
    VERCODE_TIMEOUT("40004", "验证码已超时，请重新输入"),
    MENU_IS_CHINA("41000", "该菜单下存在自己菜单，不可删除!");

    /**
     * 错误码
     */
    private String code;
    /**
     * 错误消息
     */
    private String msg;
}
