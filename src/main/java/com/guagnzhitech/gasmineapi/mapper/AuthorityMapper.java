package com.guagnzhitech.gasmineapi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.guagnzhitech.gasmineapi.model.Authority;

@Mapper
public interface AuthorityMapper {

    Authority findByAuthority(String authority);

}
