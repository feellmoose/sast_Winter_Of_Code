package com.sast.woc.ResultData;

import lombok.Data;

@Data
public class ResultData<T> {
    private Boolean success;
    private Integer errCode;
    private String errMsg;
    private T data;


    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setSuccess(true);
        resultData.setData(data);
        return resultData;
    }
    public static  ResultData success() {
        ResultData resultData = new ResultData<>();
        resultData.setSuccess(true);
        return resultData;
    }



    public static <T> ResultData<T> fail(int code,String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setSuccess(false);
        resultData.setErrCode(code);
        resultData.setErrMsg(message);
        return resultData;
    }





}
