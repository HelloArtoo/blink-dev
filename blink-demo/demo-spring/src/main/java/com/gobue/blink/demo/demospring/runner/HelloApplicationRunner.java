package com.gobue.blink.demo.demospring.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(2)
public class HelloApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("args:" + Arrays.toString(args.getSourceArgs()));
        System.out.println("### HelloApplicationRunner invoke.");
    }
}
