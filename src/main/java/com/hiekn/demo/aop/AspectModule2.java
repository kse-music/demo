package com.hiekn.demo.aop;

import org.springframework.stereotype.Component;

@Component
public class AspectModule2 {

	/** 
	 * This is the method which I would like to execute
	 * before a selected method execution.
	 */
	public void beforeAdvice(){
		System.out.println("之前");
	}
	/** 
	 * This is the method which I would like to execute
	 * after a selected method execution.
	 */
	public void afterAdvice(){
		System.out.println("之后");
	}
	/** 
	 * This is the method which I would like to execute
	 * when any method returns.
	 */
	public void afterReturningAdvice(Object retVal){
		System.out.println("返回:" + retVal.toString() );
	}
	/**
	 * This is the method which I would like to execute
	 * if there is an exception raised by any method.
	 */
	public void AfterThrowingAdvice(IllegalArgumentException ex){
		System.out.println("异常: " + ex.toString());   
	}  
}
