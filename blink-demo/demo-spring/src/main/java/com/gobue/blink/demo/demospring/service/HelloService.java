package com.gobue.blink.demo.demospring.service;

import com.gobue.blink.demo.demospring.event.HelloEvent;
import com.gobue.blink.demo.demospring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    ApplicationContext applicationContext;


    public String hello(User user) {
        System.out.println("###   invoke hello(User user)");
        applicationContext.publishEvent(new HelloEvent(this, user));
        System.out.println("###   bye ##");
        return "hello: " + user;
    }

    @Async
    public void asyncHello() {
        System.out.println("异步Hello : " + Thread.currentThread().getName());
    }

    public void syncHello() {
        System.out.println("同步Hello : " + Thread.currentThread().getName());
    }

    @Cacheable(cacheNames = "user", key = "#id")
    public User getUser(Long id) {
        return new User(id, "胡玲", "华为HR专员");
    }
}
