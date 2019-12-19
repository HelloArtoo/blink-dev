package com.gobue.blink.demo.demospring.autoconfig;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class MyEnvConfig implements EnvironmentAware {

    private Environment env;

    public MyEnvConfig() {
        System.out.println("################ MyEnvConfig registered ###############");
    }

    public String getStringValue(String key) {
        return env.getProperty(key);
    }

    public long getLongValue(String key) {
        String value = getStringValue(key);
        return Long.parseLong(value);
    }

    public int getIntValue(String key) {
        String value = getStringValue(key);
        return Integer.parseInt(value);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
