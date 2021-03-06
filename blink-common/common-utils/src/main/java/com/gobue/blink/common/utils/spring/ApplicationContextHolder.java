package com.gobue.blink.common.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHolder implements ApplicationContextAware {

    public static ApplicationContext context;
    public static final ApplicationContextHolder INSTANCE = new ApplicationContextHolder();
    private ApplicationContextHolder(){}

    public static ApplicationContextHolder getInstance() {
        return INSTANCE;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    public static <T>T getBean(Class<T> cls){
        return context.getBean(cls);
    }

}
