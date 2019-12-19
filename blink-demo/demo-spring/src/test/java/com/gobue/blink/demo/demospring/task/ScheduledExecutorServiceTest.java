package com.gobue.blink.demo.demospring.task;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {

    @Test
    public void test() throws InterruptedException {

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        // 参数：1、任务体 2、首次执行的延时时间
        //      3、任务执行间隔 4、间隔时间单位
        service.scheduleAtFixedRate(() -> System.out.println("task ScheduledExecutorService " + new Date()), 0, 3, TimeUnit.SECONDS);
        Thread.sleep(10000);
    }
}
