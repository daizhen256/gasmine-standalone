package com.guagnzhitech.gasmineapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.guagnzhitech.gasmineapi.mapper.UserMapper;
import com.guagnzhitech.gasmineapi.model.User;
import com.guagnzhitech.gasmineapi.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User gYeMian(User u) {
        User user = mapper.findByUsername(u.getUsername());
        return user;
    }

    @Override
    public int sYeMian(User u) {
        int i = 0;
        return i;
    }
}
