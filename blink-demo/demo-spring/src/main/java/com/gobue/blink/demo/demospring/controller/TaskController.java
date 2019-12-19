package com.gobue.blink.demo.demospring.controller;

import com.gobue.blink.demo.demospring.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello/task")
    public String publishTask() {
        System.out.println("Hello : " + Thread.currentThread().getName());
        helloService.asyncHello();
        helloService.syncHello();
        return "success";
    }
}
