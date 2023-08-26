package com.stackroute.myfirstspringbootrest.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	
	@Pointcut("execution(* com.stackroute.*.controller.*Controller.get*(..))")
	public void getMethodExpression() {}
	
	@Before("getMethodExpression()")
//	@Before("execution(* com.stackroute.*.*.*.*(..))")
	public void beforeAdvice(JoinPoint joinpoint) {
		logger.info(
				joinpoint.getSignature().getName()
				+" Method started its execution with "
				+Arrays.toString(joinpoint.getArgs()));
	}
	
	@AfterThrowing(pointcut = "execution(* com.stackroute.*.service.*.*(..))",throwing = "exception")
	public void afterThrowingAdvice(JoinPoint joinpoint, Exception exception) {
		logger.info(
				joinpoint.getSignature().getName()
				+" Method throwing exception "+exception);
	}
	
	@AfterReturning(pointcut = "getMethodExpression()",returning = "returnValue")
	public void afterReturningAdvice(JoinPoint joinpoint,Object returnValue) {
		logger.info(
				joinpoint.getSignature().getName()
				+" Method Returns Value: "+returnValue.toString());
	}
	
	@After("getMethodExpression()")
	public void afterAdvice(JoinPoint joinpoint) {
		logger.info(
				joinpoint.getSignature().getName()
				+" Method completed its execution with");
	}
	
	@Around("getMethodExpression()")
	public Object aroundAdvice(ProceedingJoinPoint joinpoint) throws Throwable {
		System.out.println("Proceeding joinpoint");		
		Object returnValue = joinpoint.proceed();
		return returnValue;
	}
}
