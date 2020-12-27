package com.example.as.api.uitl;

public enum ResponseCode {
    RC_SUCCESS(0,"SUCCESS"),
    RC_ACCOUNT_INVALID(5001,"账号不存在"),
    RC_PWD_INVALID(5002,"密码错误"),
    RC_NEED_LOGIN(5003,"请先登录"),
    RC_USER_FORBID(6001,"用户被禁用"),
    ;


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private int code;
    private String msg;

    ResponseCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
