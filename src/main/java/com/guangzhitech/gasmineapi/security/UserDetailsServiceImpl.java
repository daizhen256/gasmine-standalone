package com.guangzhitech.gasmineapi.security;

import java.io.Serializable;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guangzhitech.gasmineapi.dao.UserDao;
import com.guangzhitech.gasmineapi.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

    @Autowired
    private UserDao userMapper;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
    	user = userMapper.getByLoginName(user);
        if (user == null) {
            throw new UsernameNotFoundException("username " + username + " not found");
        }
        Hibernate.initialize(user.getAuthorities());
        return user;
    }

}
