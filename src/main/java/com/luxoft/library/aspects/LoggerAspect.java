package com.luxoft.library.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {

    private Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Pointcut("execution(* com.luxoft.library.controller.*.*(..))")
    public void selectAllMethodsAvailable() {
    }

    @After("selectAllMethodsAvailable()")
    public void afterAdvice(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.log(Level.INFO, "название метода: " + methodName);
    }

    @AfterReturning(pointcut = "selectAllMethodsAvailable()", returning = "someValue")
    public void afterReturningAdvice(Object someValue) {
        logger.log(Level.INFO, "возвращенное значение: " + someValue.toString());
    }

    @Around("@annotation(com.luxoft.library.aspects.interfaces.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.log(Level.INFO, joinPoint.getSignature() + " выполнен за " + executionTime + "мс");
        return proceed;
    }

}
