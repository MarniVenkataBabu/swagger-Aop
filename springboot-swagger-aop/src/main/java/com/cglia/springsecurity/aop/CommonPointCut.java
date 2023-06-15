package com.cglia.springsecurity.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCut {

    @Pointcut("execution(* com.cglia.springsecurity.controller.CustomerController.*(..)))")
    public void customerControllerLog(){}

    /*
        we want to consider all the methods within the classes defined in package com.example and subpackage it
     */
    @Pointcut("within(com.cglia..*)")
    public void customerControllerLog3(){}
}
