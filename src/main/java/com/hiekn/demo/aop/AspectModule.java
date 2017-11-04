package com.hiekn.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectModule {

	/** Following is the definition for a pointcut to select
	 *  all the methods available. So advice will be called
	 *  for all the methods.
	 */
	@Pointcut("execution(* com.hiekn.demo.service.impl.*.*(..))")
	private void selectAll(){}
	/** 
	 * This is the method which I would like to execute
	 * before a selected method execution.
	 */
	@Before("selectAll()")
	public void beforeAdvice(){
		System.out.println("beforeAdvice");
	}
	@Around("selectAll()")
	public Object log(ProceedingJoinPoint p) throws Throwable{
		System.out.println("around前");
		Object obj = p.proceed();
		System.out.println("around后");
		return obj;
	}
	/** 
	 * This is the method which I would like to execute
	 * after a selected method execution.
	 */
	@After("selectAll()")
	public void afterAdvice(){
		System.out.println("afterAdvice");
	}
	/** 
	 * This is the method which I would like to execute
	 * when any method returns.
	 */
	@AfterReturning(pointcut = "selectAll()", returning="retVal")
	public void afterReturningAdvice(Object retVal){
		System.out.println("Returning:");
	}
	/**
	 * This is the method which I would like to execute
	 * if there is an exception raised by any method.
	 */
	@AfterThrowing(pointcut = "selectAll()", throwing = "ex")
	public void AfterThrowingAdvice(IllegalArgumentException ex){
		System.out.println("AfterThrowingAdvice" + ex.toString());   
	}  
}
