package com.sast.woc.ResultData;

public class MyException extends RuntimeException {
    private Integer code;
    private String message;

    public MyException(ReturnCode returnCode) {
        this.code = returnCode.getCode();
        this.message = returnCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
