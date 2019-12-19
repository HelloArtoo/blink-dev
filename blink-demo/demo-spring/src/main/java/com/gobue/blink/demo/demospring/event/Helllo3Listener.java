package com.gobue.blink.demo.demospring.event;

import com.gobue.blink.demo.demospring.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Helllo3Listener implements ApplicationListener<HelloEvent> {

    public void onApplicationEvent(HelloEvent helloEvent) {
        User user = helloEvent.getUser();
        System.out.println("##HelloListene3 r##[monitor user:" + user + " says hello.], i am hello3");
    }
}
