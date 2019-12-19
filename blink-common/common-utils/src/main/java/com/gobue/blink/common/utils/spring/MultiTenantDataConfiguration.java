package com.gobue.blink.common.utils.spring;

import com.jd.y.ipc.saas.common.database.MultiTenantDataSource;
import com.jd.y.ipc.saas.common.database.SaaSDatabaseConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
public class MultiTenantDataConfiguration implements BeanPostProcessor {

    private SaaSDatabaseConfig config;

    @Bean
    public SaaSDatabaseConfig getConfig(){
        SaaSDatabaseConfig saaSDatabaseConfig = new SaaSDatabaseConfig();
        this.config = saaSDatabaseConfig;
        return saaSDatabaseConfig;
    }

    @Bean(name="masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    DataSource getDataSource(SaaSDatabaseConfig saaSDatabaseConfig) {
        return DataSourceBuilder.create().build();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {

        if (bean instanceof DataSource) {
            return new MultiTenantDataSource((DataSource) bean,
                    this.config);
        }
        return bean;
    }


}
