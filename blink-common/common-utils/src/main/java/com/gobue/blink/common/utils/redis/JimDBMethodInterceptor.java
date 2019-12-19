package com.gobue.blink.common.utils.redis;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class JimDBMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if ("multi".equals(methodInvocation.getMethod().getName())) {
            return null;
        }
        if ("exec".equals(methodInvocation.getMethod().getName())) {
            return null;
        }
        return methodInvocation.proceed();
    }
}