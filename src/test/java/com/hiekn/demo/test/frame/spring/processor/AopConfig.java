package com.hiekn.demo.test.frame.spring.processor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * DefaultAopProxyFactory:代理创建行为,即便是选择了cglib代理，但如果目标实现了接口，最终还是会选择jdk做代理
 */
@Aspect
@EnableAspectJAutoProxy
@Component
public class AopConfig {

    @Around("execution(* com.hiekn.demo.test.frame.spring.processor.SingleResearchSpring.*(..))")
    public Object around(ProceedingJoinPoint p) throws Throwable {
        System.out.println("proceed 前");
        Object obj = p.proceed();
        System.out.println("proceed 后");
        return obj;
    }

}