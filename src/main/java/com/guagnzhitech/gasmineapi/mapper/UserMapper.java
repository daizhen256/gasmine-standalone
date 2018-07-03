package com.guagnzhitech.gasmineapi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.guagnzhitech.gasmineapi.model.User;

@Mapper
public interface UserMapper {

    User findByUsername(String username);

}
