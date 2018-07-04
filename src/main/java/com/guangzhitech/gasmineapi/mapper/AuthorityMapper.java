package com.guangzhitech.gasmineapi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.guangzhitech.gasmineapi.model.Authority;

@Mapper
public interface AuthorityMapper {

    Authority findByAuthority(String authority);
    
    int save(Authority authority);

}
