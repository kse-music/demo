package com.hiekn.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopTest{
	
	@Around("execution(* com.hiekn.demo.service.impl.*.*(..)) && @annotation(com.hiekn.demo.aop.Intercept)")
	public Object log(ProceedingJoinPoint p) throws Throwable{
		System.out.println("around前2");
		Object obj = p.proceed();
		System.out.println("around后2");
		return obj;
	}
}
