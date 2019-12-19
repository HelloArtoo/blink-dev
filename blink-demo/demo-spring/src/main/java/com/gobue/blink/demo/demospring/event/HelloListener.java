package com.gobue.blink.demo.demospring.event;

import com.gobue.blink.demo.demospring.model.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HelloListener {


    @EventListener
    public void hello(HelloEvent helloEvent) {

        User user = helloEvent.getUser();
        System.out.println("##HelloListener##[monitor user:" + user + " says hello.], i am hello1");
    }

    @EventListener
    public void hello2(HelloEvent helloEvent) {

        User user = helloEvent.getUser();
        System.out.println("##HelloListener2 ##[monitor user:" + user + " says hello.], i am hello2");
    }


}
