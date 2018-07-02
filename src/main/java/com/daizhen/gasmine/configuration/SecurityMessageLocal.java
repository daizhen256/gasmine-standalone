package com.daizhen.gasmine.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
 
import java.util.Locale;
@Configuration
public class SecurityMessageLocal {
 
    @Bean
    public MessageSource messageSource() {
        Locale.setDefault(Locale.CHINA);
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.addBasenames("classpath:org/springframework/security/messages_zh_CN");
        messageSource.addBasenames("classpath:com/daizhen/gasmine/configuration/messages_zh_CN");
 
        return messageSource;
    }
}