package com.gobue.blink.common.utils.async;

import com.jd.y.ipc.saas.common.model.SaaSContext;
import com.jd.y.ipc.saas.common.utils.ThreadLocalUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AsyncThreadPoolExecutor extends ThreadPoolTaskExecutor {

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        ExecutorService executorService = getThreadPoolExecutor();
        final SaaSContext saaSContext = ThreadLocalUtils.getSaaSContext();
        final SaaSContext transform = SaaSContext.of(saaSContext);

        return executorService.submit(() -> {
            try {
                ThreadLocalUtils.setSaaSContext(transform);
                T future = task.call();
                return future;
            } finally {
                ThreadLocalUtils.removeSaaSContext();
            }
        });

    }

    @Override
    public Future<?> submit(Runnable task) {
        ExecutorService executorService = getThreadPoolExecutor();
        final SaaSContext saaSContext = ThreadLocalUtils.getSaaSContext();
        final SaaSContext transform = SaaSContext.of(saaSContext);

        return executorService.submit(()->{
            try {
                ThreadLocalUtils.setSaaSContext(transform);
                task.run();
            } finally {
                ThreadLocalUtils.removeSaaSContext();
            }
        });
    }

    @Override
    public void execute(Runnable task) {
        ExecutorService executorService = getThreadPoolExecutor();
        final SaaSContext saaSContext = ThreadLocalUtils.getSaaSContext();
        final SaaSContext transform = SaaSContext.of(saaSContext);

        executorService.execute(() -> {
            try {
                ThreadLocalUtils.setSaaSContext(transform);
                task.run();
            }finally {
                ThreadLocalUtils.removeSaaSContext();
            }
        });
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        this.execute(task);
    }

}
