package com.gobue.blink.demo.demospring.event;

import com.gobue.blink.demo.demospring.model.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class HelloEvent extends ApplicationEvent {

    private User user;

    public HelloEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
