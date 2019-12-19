package com.gobue.blink.demo.demospring.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(1)
public class HelloCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("args:" + Arrays.toString(args));
        System.out.println("### HelloCommandLineRunner invoke.");
    }
}
