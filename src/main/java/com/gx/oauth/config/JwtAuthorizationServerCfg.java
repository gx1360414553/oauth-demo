package com.gx.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @Title
 * @Description JwtAuthorizationServerCfg
 * @Program oauth-demo
 * @Author xiong5.gao
 * @Version 1.0
 * @Date 2024-04-18 20:20
 * @Copyright Copyright (c) 2024 TCL Inc. All rights reserved
 **/
@Configuration
@EnableAuthorizationServer   // 指定为授权服务器
public class JwtAuthorizationServerCfg extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    /* ==================配置客户端信息============= */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(myClientDetailsService());
    }

    @Bean
    public ClientDetailsService myClientDetailsService() {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        // 由于我在数据库使用了BCryptPasswordEncoder加密，这里要告诉jdbcDetailsService
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }

    /* ==================配置令牌存储信息============= */
    @Bean
    public TokenStore tokenStore() {
        // 这里需要编写一个jwt转换器来将jwt转换为对象
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    private JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 使用同一个密码来编码JWT中的OAuth2令牌。在生产环境建议加密
        jwtAccessTokenConverter.setSigningKey("123");
        return jwtAccessTokenConverter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager);
    }
}
