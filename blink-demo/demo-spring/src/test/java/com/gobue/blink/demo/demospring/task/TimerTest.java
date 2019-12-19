package com.gobue.blink.demo.demospring.task;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    @Test
    public void test() throws InterruptedException {

        final int[] count = {0};
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(count[0]++);
            }
        }, 3000, 1000);

       Thread.sleep(30000);
    }
}
