package com.guangzhitech.gasmineapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.guangzhitech.gasmineapi.dao.AuthorityDao;
import com.guangzhitech.gasmineapi.dao.UserDao;
import com.guangzhitech.gasmineapi.model.Authority;
import com.guangzhitech.gasmineapi.model.User;
import com.guangzhitech.gasmineapi.support.PassUtil;

@SpringBootApplication
@MapperScan(value = "com.guangzhitech.gasmineapi.dao")
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(DemoApplication.class, args);
        UserDao userMapper = context.getBean(UserDao.class);
        AuthorityDao authorityRepository = context.getBean(AuthorityDao.class);

        Authority adminAuthority = getOrGreateAuthority("ROLE_ADMIN", authorityRepository);
        Authority basicAuthority = getOrGreateAuthority("ROLE_BASIC", authorityRepository);

        User admin = new User("admin", "123456");
        encodePassword(admin);
        admin.getAuthorities().add(adminAuthority);
        admin.getAuthorities().add(basicAuthority);

        User test = new User("test", "test");
        encodePassword(test);
        test.getAuthorities().add(basicAuthority);

        userMapper.insert(admin);
        userMapper.insert(test);
    }

    private static void encodePassword(User user) {
        user.setPassword(PassUtil.encode(user.getUsername(), user.getPassword()));
    }

    private static Authority getOrGreateAuthority(String authorityText, AuthorityDao Authoritydao) {
        Authority authority = Authoritydao.findByAuthority(authorityText);
        if (authority == null) {
            authority = new Authority(authorityText);
            Authoritydao.insert(authority);
        }
        return authority;
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
