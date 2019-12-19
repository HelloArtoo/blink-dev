package com.gobue.blink.demo.demospring.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrintYesTask {


    @Scheduled(cron = "0/10 * * * * *")
    public void print() {
        System.out.println("YES");
    }
}
