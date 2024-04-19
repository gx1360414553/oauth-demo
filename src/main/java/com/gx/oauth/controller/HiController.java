package com.gx.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title
 * @Description HiController
 * @Program oauth-demo
 * @Author xiong5.gao
 * @Version 1.0
 * @Date 2024-04-18 17:38
 * @Copyright Copyright (c) 2024 TCL Inc. All rights reserved
 **/
@RestController
public class HiController {
    @RequestMapping("/")
    public String index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "<h1>HI Spring Security Resource Server</h1>";
    }
    @RequestMapping("/hi")
    public String hi() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("身份信息：authentication.getPrincipal() = " + authentication.getPrincipal());
        System.out.println("权限信息：authentication.getAuthorities() = " + authentication.getAuthorities());
        System.out.println("凭证信息：authentication.getCredentials() = " + authentication.getCredentials());
        return "<h1>HI Spring Security</h1>";
    }
}

