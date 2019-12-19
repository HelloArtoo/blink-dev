package com.gobue.blink.demo.demospring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "say.hello")
@Data
public class HelloConfig {

    private String CN;
    private String UK;

}
