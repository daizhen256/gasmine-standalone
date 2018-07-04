package com.guangzhitech.gasmineapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.guangzhitech.gasmineapi.model.User;

@Mapper
public interface UserMapper {

    User findByUsername(String username);
    List<User> findAll();
    int save(User user);
    User getOne(Long id);

}
