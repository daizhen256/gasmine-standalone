package com.guangzhitech.gasmineapi.security;

import java.io.Serializable;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guangzhitech.gasmineapi.mapper.UserMapper;
import com.guangzhitech.gasmineapi.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username " + username + " not found");
        }
        Hibernate.initialize(user.getAuthorities());
        return user;
    }

}
