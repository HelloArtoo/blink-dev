package com.gobue.blink.common.utils.utils;

import com.jd.y.ipc.saas.common.model.SaaSContext;
import com.jd.y.ipc.saas.common.spring.ApplicationContextHolder;

import java.util.Objects;

public class ThreadLocalUtils {
    private static final ThreadLocal <SaaSContext> accountThreadLocal = new ThreadLocal <>();

    private static final ThreadLocal <Long> taskIdThreadLocal = new ThreadLocal <>();

    public static SaaSContext getSaaSContext() {
        SaaSContext currentThreadAccount = accountThreadLocal.get();
        if (Objects.nonNull(currentThreadAccount)) {
            return currentThreadAccount;
        } else {
            return ApplicationContextHolder.context.getBean(SaaSContext.class);
        }
    }


    public static void setSaaSContext(SaaSContext accountHolder) {
        accountThreadLocal.set(accountHolder);
    }

    public static void removeSaaSContext(){
        accountThreadLocal.set(null);
        accountThreadLocal.remove();
    }

    public static Long getTaskId() {
        return taskIdThreadLocal.get();
    }

    public static void setTaskId(Long taskId) {
        taskIdThreadLocal.set(taskId);
    }

    public static void removeTaskId() {
        taskIdThreadLocal.set(null);
        taskIdThreadLocal.remove();
    }


}

