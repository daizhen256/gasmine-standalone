package com.guangzhitech.gasmineapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

import com.guangzhitech.gasmineapi.support.BaseModel;

@Entity
public class Authority extends BaseModel implements GrantedAuthority {

    private String authority;

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    @Column(length = 20, nullable = false, unique = true)
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
