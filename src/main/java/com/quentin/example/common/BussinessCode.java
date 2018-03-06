package com.quentin.example.common;

/**
 * 返回代码统一枚举类
 *
 * @Author Created by guoqun.yang
 * @Date Created in 16:10 2018/3/6
 * @Version 1.0
 */
public enum BussinessCode {
    // 成功
    GLOBAL_SUCCESS("0000", "成功"),
    //失败
    GLOBAL_ERROR("9999", "系统正在维护中,请稍后再试!"),
    //通用
    GLOBAL_LOGIN_NAME_NULL("0501", "用户名不能为空"),
    GLOBAL_LOGIN_PASS_NULL("0502", "密码不能为空"),
    GLOBAL_LOGIN_FAIL("0503", "用户名或密码不匹配"),
    GLOBAL_LOGIN_ERROR("0504", "系统登录异常");

    private String code;
    private String msg;

    BussinessCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
