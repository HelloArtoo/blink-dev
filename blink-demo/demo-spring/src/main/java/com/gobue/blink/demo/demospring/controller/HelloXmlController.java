package com.gobue.blink.demo.demospring.controller;

import com.gobue.blink.demo.demospring.model.UserXml;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloXmlController {

    @GetMapping(value = "/user")
    public UserXml user() {
        UserXml userXml = new UserXml();
        userXml.setName("jack");
        userXml.setPosition("专家");
        return userXml;
    }

}
