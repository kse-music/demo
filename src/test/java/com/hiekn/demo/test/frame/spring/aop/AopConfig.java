package com.hiekn.demo.test.frame.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * DefaultAopProxyFactory:代理创建行为,即便是选择了cglib代理，但如果目标实现了接口，最终还是会选择jdk做代理
 */
@Aspect
@EnableAspectJAutoProxy
public class AopConfig {
	
	@Around("execution(* com.hiekn.demo.test.frame.spring.processor.TestConfiguration.*(..))")
	public Object log(ProceedingJoinPoint p) throws Throwable{
		System.out.println("around前");
		Object obj = p.proceed();
		System.out.println("around后");
		return obj;
	}

}
