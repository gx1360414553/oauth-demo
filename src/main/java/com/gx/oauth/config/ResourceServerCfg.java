//package com.gx.oauth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//
//import javax.sql.DataSource;
//
///**
// * @Title
// * @Description ResourceServerCfg
// * @Program oauth-demo
// * @Author xiong5.gao
// * @Version 1.0
// * @Date 2024-04-18 19:43
// * @Copyright Copyright (c) 2024 TCL Inc. All rights reserved
// **/
//@Configuration
//// 开启资源服务器
//@EnableResourceServer
//public class ResourceServerCfg extends ResourceServerConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenStore(new JdbcTokenStore(dataSource));
//    }
//
//    //Http安全配置，对每个到达系统的http请求链接进行校验
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //所有请求必须认证通过
//        http.authorizeRequests()
//                //下边的路径放行
//                .antMatchers("/oauth/**").permitAll()
//                .anyRequest().authenticated();
//    }
//}
//
