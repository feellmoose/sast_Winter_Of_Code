package com.sast.woc.ResultData;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 服务异常
     * @param e
     * @return
     */

    @ExceptionHandler(MyException.class)
    public ResultData<String> exception(MyException e) {
        return ResultData.fail(e.getCode(),e.getMessage());
    }

    /**
     * 系统默认异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        return ResultData.fail(ReturnCode.RC500.getCode(),e.getMessage());
    }

}

