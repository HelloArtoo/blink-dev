package com.gobue.blink.common.utils.spring;

import com.jd.y.ipc.saas.common.async.AsyncThreadPoolConfig;
import com.jd.y.ipc.saas.common.async.AsyncThreadPoolExecutor;
import com.jd.y.ipc.saas.common.model.SaaSContext;
import com.jd.y.ipc.saas.common.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableConfigurationProperties(AsyncThreadPoolConfig.class)
public class AsyncConfiguration extends AsyncConfigurerSupport{

    @Autowired
    AsyncThreadPoolConfig asyncThreadPoolConfig;


    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolExecutor();
        executor.setCorePoolSize(asyncThreadPoolConfig.getCorePoolSize());
        executor.setMaxPoolSize(asyncThreadPoolConfig.getMaxPoolSize());
        executor.setQueueCapacity(asyncThreadPoolConfig.getQueueCapacity());
        executor.setThreadNamePrefix("Async(IPC)-");
        executor.initialize();
        return executor;
    }

}
