package com.gobue.blink.demo.demospring.controller;

import com.gobue.blink.demo.demospring.config.HelloConfig;
import com.gobue.blink.demo.demospring.model.User;
import com.gobue.blink.demo.demospring.service.HelloService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    HelloConfig helloConfig;

    @GetMapping("/")
    public String rootPath() {
        return "the demo application is running.  config: " + helloConfig;
    }

    @GetMapping("/hello")
    public String hello() {
        return helloService.hello(new User(1L, "artoo", "董事长"));
    }

    @GetMapping("/bye/{userName}")
    public String bye(@PathVariable String userName) {
        return "bye " + userName;
    }
}
