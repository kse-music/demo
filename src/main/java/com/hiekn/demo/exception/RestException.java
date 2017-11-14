package com.hiekn.demo.exception;


import com.hiekn.demo.bean.result.ErrorCode;

public class RestException extends BaseException{

    private static final long serialVersionUID = 1L;

    public RestException(ErrorCode code) {
        super(code);
    }

    public static RestException newInstance(){
        return newInstance(ErrorCode.PARAM_ERROR);
    }

    public static RestException newInstance(ErrorCode code){
        return new RestException(code);
    }

}
