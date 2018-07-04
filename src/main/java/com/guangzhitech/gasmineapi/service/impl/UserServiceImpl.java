package com.guangzhitech.gasmineapi.service.impl;

import javax.annotation.Resource;

import com.guangzhitech.gasmineapi.dao.UserDao;
import com.guangzhitech.gasmineapi.model.User;
import com.guangzhitech.gasmineapi.service.UserService;

public class UserServiceImpl implements UserService {

	@Resource
    private UserDao mapper;

    @Override
    public User gYeMian(User u) {
        User user = mapper.getByLoginName(u);
        return user;
    }

    @Override
    public int sYeMian(User u) {
        int i = 0;
        return i;
    }
}
