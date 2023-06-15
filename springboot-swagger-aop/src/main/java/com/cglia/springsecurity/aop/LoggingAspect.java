package com.cglia.springsecurity.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @Before("execution(* com.cglia.springsecurity.controller.CustomerController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        // Logging
        LOGGER.info("Calling method {} in {}", methodName, className);

        // Get argument values
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Method arguments: {}", Arrays.toString(args));
        LOGGER.info("JoinPoint: {}", joinPoint);
    }

    @After("execution(* com.cglia.springsecurity.controller.CustomerController.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        // Logging
        LOGGER.info("Finishing after advice method 1");
    }

    @Pointcut("execution(* com.cglia.springsecurity.controller.CustomerController.*(..))")
    public void customerControllerLog2() {
    }

    @After("customerControllerLog2()")
    public void afterAdvice2(JoinPoint joinPoint) {
        // Logging
        LOGGER.info("Finishing after advice method 2");
    }

    @After("com.cglia.springsecurity.aop.CommonPointCut.customerControllerLog()")
    public void afterAdvice3(JoinPoint joinPoint) {
        // Logging
        LOGGER.info("after Advice method 3");
    }

    @Around("execution(* com.cglia.springsecurity.controller.CustomerController.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        // Log method execution time
        LOGGER.info("Execution time of {}: {} ms", proceedingJoinPoint.getSignature().toShortString(), stopWatch.getTotalTimeMillis());

        return result;
    }
}
