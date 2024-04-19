//package com.gx.oauth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//
//@Configuration
//@EnableAuthorizationServer   // 指定为授权服务器
//public class AuthorizationServerCfg extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    public PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserDetailsService userDetailsService;
//
//    @Autowired
//    public AuthenticationManager authenticationManager;
//
//    /**
//     * 用来配置授权服务器可以为哪些客户端授权
//     *
//     * @param clients 需要初始化一个客户端信息（clientID、secret、redirectURI、使用哪种授权模式）
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().withClient("hx-01")
//                // 明文报错 java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
//                .secret(passwordEncoder.encode("123"))
//                .redirectUris("http://localhost:9999")
//                // 授权服务器支持的模式：授权码模式、刷新令牌
//                .authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
//                // 令牌允许获取的资源权限
//                .scopes("read:user");
//        // 1. 请求用户是否授权
//        // 访问：localhost:port/oauth/authorize?client_id=id&response_type=code&redirect_uri=
//        // http://localhost:8081/oauth/authorize?client_id=hx-01&response_type=code&redirect_uri=http://localhost:9999
//        // 跳转http://localhost:9999/?code=yN6qXh
//        // 2. 授权之后根据授权码获取令牌 /oauth/token id secret redirectURI code
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        // authorization_code模式需要配置授权服务使用哪个userDetailsService
//        endpoints.userDetailsService(userDetailsService);
//        // 对于密码模式是使用用户名密码进行一个颁发，所以需要让授权服务知道我们的authenticationManager。只有知道authenticationManager后才知道我们使用的认证方式
//        endpoints.authenticationManager(authenticationManager);
//    }
//}
