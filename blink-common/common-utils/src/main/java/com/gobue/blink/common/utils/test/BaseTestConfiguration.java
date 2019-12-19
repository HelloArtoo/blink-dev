package com.gobue.blink.common.utils.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.y.ipc.saas.common.spring.ApplicationContextHolder;
import com.jd.y.ipc.saas.common.test.dbunit.TestDatabaseConnection;
import com.jd.y.ipc.saas.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class BaseTestConfiguration {

    @Bean(name = "testDatabaseConnection")
    public TestDatabaseConnection postgresDatabaseConnection(DataSource dataSource)
            throws SQLException {
        return new TestDatabaseConnection(dataSource);
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return JsonUtils.OBJECT_MAPPER;
    }

    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return ApplicationContextHolder.getInstance();
    }
}
