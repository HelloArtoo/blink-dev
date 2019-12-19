package com.gobue.blink.common.utils.database;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "saas.database")
public class SaaSDatabaseConfig {
    Map<String,Object> connection = new HashMap<>();
}
