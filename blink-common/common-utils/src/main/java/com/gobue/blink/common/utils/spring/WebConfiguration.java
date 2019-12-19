package com.gobue.blink.common.utils.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.y.ipc.saas.common.AppConstants;
import com.jd.y.ipc.saas.common.model.SaaSContext;
import com.jd.y.ipc.saas.common.spring.web.AppExceptionHandlerController;
import com.jd.y.ipc.saas.common.utils.JsonUtils;
import com.jd.y.ipc.saas.common.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

@Configuration
@Slf4j
public class WebConfiguration extends WebMvcConfigurationSupport{

    // exception handler
    @Bean
    public AppExceptionHandlerController appExceptionHandlerController() {
        return new AppExceptionHandlerController();
    }


    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SaaSContext newAccountHolder(HttpServletRequest request) {
        String tenantCode = request.getHeader(AppConstants.HEADER_TENANT_CODE);
        String accountId = request.getHeader(AppConstants.HEADER_ACCOUNT_ID);

        if (StringUtils.isNotEmpty(tenantCode) && StringUtils.isNotEmpty(accountId)) {
            return new SaaSContext(tenantCode, accountId);
        } else {
            log.info("tenant code [{}] or account id [{}] are empty", tenantCode, accountId);
        }
        return new SaaSContext();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                ThreadLocalUtils.removeSaaSContext();
                log.trace("clean up thread local after completion");
            }
        });
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(new PageRequest(0, Integer.MAX_VALUE));
        resolver.setMaxPageSize(Integer.MAX_VALUE);
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
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


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.removeIf(c-> c instanceof StringHttpMessageConverter);
        converters.add(new StringHttpMessageConverter(
                Charset.forName("UTF-8")));

        converters.removeIf(c-> c instanceof MappingJackson2HttpMessageConverter);
        converters.add(new MappingJackson2HttpMessageConverter( JsonUtils.OBJECT_MAPPER));


    }
}
