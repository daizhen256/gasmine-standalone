package com.guangzhitech.gasmineapi.service.impl;

import javax.annotation.Resource;

import com.guangzhitech.gasmineapi.mapper.UserMapper;
import com.guangzhitech.gasmineapi.model.User;
import com.guangzhitech.gasmineapi.service.UserService;

public class UserServiceImpl implements UserService {

	@Resource
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
