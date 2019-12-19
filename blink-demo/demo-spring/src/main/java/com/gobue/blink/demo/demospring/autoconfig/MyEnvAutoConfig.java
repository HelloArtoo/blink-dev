package com.gobue.blink.demo.demospring.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertyResolver;

/**
 * Program arguments 添加参数 --debug 可以看到是否注册成功
 */
@Configuration
@ConditionalOnClass(PropertyResolver.class)
public class MyEnvAutoConfig {

    @Bean
    public MyEnvConfig envConfig() {
        return new MyEnvConfig();
    }

}
