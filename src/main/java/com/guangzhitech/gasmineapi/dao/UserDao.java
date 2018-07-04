package com.guangzhitech.gasmineapi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.guangzhitech.gasmineapi.model.User;

@Mapper
public interface UserDao {

    public User getByLoginName(User user);
    public List<User> findAll();
    public int insert(User user);
    public User get(Long id);

}
