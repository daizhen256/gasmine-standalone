package com.guangzhitech.gasmineapi.config;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.guangzhitech.gasmineapi.support.JsonArgumentResolver;

@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonArgumentResolver());
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setUrlPatterns(Collections.singletonList("/api/*"));
        bean.setFilter(new OpenSessionInViewFilter());
        return bean;
    }

}
