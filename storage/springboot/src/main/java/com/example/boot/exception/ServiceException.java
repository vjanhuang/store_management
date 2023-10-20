package com.example.boot.exception;

import com.example.boot.common.Result;
import lombok.Getter;

/**
 * 自定义异常
* 
 */
@Getter
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public ServiceException(String msg) {
        super(msg);
        this.code = Result.CODE_SYS_ERROR;
    }

}
