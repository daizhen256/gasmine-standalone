package com.guangzhitech.gasmineapi;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.guangzhitech.gasmineapi.mapper.AuthorityMapper;
import com.guangzhitech.gasmineapi.mapper.UserMapper;
import com.guangzhitech.gasmineapi.model.Authority;
import com.guangzhitech.gasmineapi.model.User;
import com.guangzhitech.gasmineapi.support.PassUtil;

@SpringBootApplication
@MapperScan(value = "com.guangzhitech.gasmineapi.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(DemoApplication.class, args);
        UserMapper userMapper = context.getBean(UserMapper.class);
        AuthorityMapper authorityRepository = context.getBean(AuthorityMapper.class);

        Authority adminAuthority = getOrGreateAuthority("ROLE_ADMIN", authorityRepository);
        Authority basicAuthority = getOrGreateAuthority("ROLE_BASIC", authorityRepository);

        User admin = new User("admin", "123456");
        encodePassword(admin);
        admin.getAuthorities().add(adminAuthority);
        admin.getAuthorities().add(basicAuthority);

        User test = new User("test", "test");
        encodePassword(test);
        test.getAuthorities().add(basicAuthority);

        userMapper.save(admin);
        userMapper.save(test);
    }

    private static void encodePassword(User user) {
        user.setPassword(PassUtil.encode(user.getUsername(), user.getPassword()));
    }

    private static Authority getOrGreateAuthority(String authorityText, AuthorityMapper authorityMapper) {
        Authority authority = authorityMapper.findByAuthority(authorityText);
        if (authority == null) {
            authority = new Authority(authorityText);
            authorityMapper.save(authority);
        }
        return authority;
    }

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public SessionFactory sessionFactory(EntityManagerFactory emf) {
        return emf.unwrap(SessionFactory.class);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:org/springframework/security/messages");

        ReloadableResourceBundleMessageSource messageSourceLocal = new ReloadableResourceBundleMessageSource();
        messageSourceLocal.setBasename("classpath:messages");
        messageSourceLocal.setParentMessageSource(messageSource);

        return messageSourceLocal;
    }

}
