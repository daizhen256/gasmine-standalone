package com.guangzhitech.gasmineapi.dao;

import org.apache.ibatis.annotations.Mapper;

import com.guangzhitech.gasmineapi.model.Authority;

@Mapper
public interface AuthorityDao {

    Authority findByAuthority(String authority);
    
    int insert(Authority authority);

}
