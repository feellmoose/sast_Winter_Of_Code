package com.sast.woc.ResultData;

public enum ReturnCode {
    /**操作成功**/
    RC100(100,"操作成功"),
    /**操作失败**/
    RC999(200,"操作失败"),
    /**服务异常**/
    RC500(500,"系统异常，请稍后重试"),

    USERNAME_ALREADY_EXISTS(1001,"用户已经存在"),
    USERNAME_OR_PASSWORD_ERROR(1002,"用户名或密码错误"),
    INVALID_TOKEN(1003,"访问令牌不合法"),
    ACCESS_DENIED(1004,"无权限"),
    USERNAME_ERROR(1005,"用户名错误");


    /**自定义状态码**/
    private final int code;
    /**自定义描述**/
    private final String message;
    //取用

    ReturnCode(int code, String message){
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
