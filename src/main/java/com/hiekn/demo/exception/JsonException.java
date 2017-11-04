package com.hiekn.demo.exception;

import com.hiekn.demo.bean.result.ErrorCode;

public class JsonException extends BaseException{
	
	private static final long serialVersionUID = 1L;
	
	public JsonException(ErrorCode code) {
		super(code);
	}

	public static JsonException newInstance(){
		return newInstance(ErrorCode.JSON_ERROR);
	}
	
	public static JsonException newInstance(ErrorCode code){
		return new JsonException(code);
	}

}
