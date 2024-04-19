package com.gx.oauth.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Title
 * @Description UserJwt
 * @Program oauth-demo
 * @Author xiong5.gao
 * @Version 1.0
 * @Date 2024-04-19 10:27
 * @Copyright Copyright (c) 2024 TCL Inc. All rights reserved
 **/
public class UserJwt extends User {
    private String id;    //用户ID
    private String name;  //用户名字

    public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
