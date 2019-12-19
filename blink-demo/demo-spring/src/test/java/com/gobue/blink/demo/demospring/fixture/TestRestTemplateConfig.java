package com.gobue.blink.demo.demospring.fixture;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestRestTemplateConfig {

    @Bean
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }

}
