package com.gobue.blink.common.utils.async;

import com.jd.y.ipc.saas.common.model.SaaSContext;
import com.jd.y.ipc.saas.common.utils.ThreadLocalUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Data
@ConfigurationProperties(prefix = "saas.async")
public class AsyncThreadPoolConfig {
    Integer maxPoolSize = 40;
    Integer corePoolSize = 40;
    Integer queueCapacity = 10000;

}
