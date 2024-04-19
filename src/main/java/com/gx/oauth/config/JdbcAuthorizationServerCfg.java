//package com.gx.oauth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//
//import javax.sql.DataSource;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Title
// * @Description JdbcAuthorizationServerCfg
// * @Program oauth-demo
// * @Author xiong5.gao
// * @Version 1.0
// * @Date 2024-04-18 17:33
// * @Copyright Copyright (c) 2024 TCL Inc. All rights reserved
// **/
//@Configuration
//@EnableAuthorizationServer   // 指定为授权服务器
//public class JdbcAuthorizationServerCfg extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    /* ==================配置客户端信息============= */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(myClientDetailsService());
//    }
//
//    @Bean
//    public ClientDetailsService myClientDetailsService() {
//        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
//        // 由于我在数据库使用了BCryptPasswordEncoder加密，这里要告诉jdbcDetailsService
//        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
//        return jdbcClientDetailsService;
//    }
//    /* ==================配置令牌存储信息============= */
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        // 为了支持密码模式
//        endpoints.authenticationManager(authenticationManager);
//        endpoints.tokenStore(tokenStore());
//        /* === 配置tokenService参数 === */
//        // 修改默认令牌生成服务
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        // 基于数据库令牌生成
//        tokenServices.setTokenStore(endpoints.getTokenStore());
//        // 是否支持刷新令牌
//        tokenServices.setSupportRefreshToken(true);
//        // 是否重复使用刷新令牌(直到过期)
//        tokenServices.setReuseRefreshToken(true);
//
//        // 设置客户端信息
//        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//        // 用来控制令牌存储增强策略
//        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        // 访问令牌的默认有效期（以秒为单位）。过期的令牌为零或负数。
//        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
//        // 刷新令牌有效性（秒）。如果小于等于0，则令牌不过期。
//        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(3));
//        // 使用配置的令牌服务
//        endpoints.tokenServices(tokenServices);
//
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
//}
//
