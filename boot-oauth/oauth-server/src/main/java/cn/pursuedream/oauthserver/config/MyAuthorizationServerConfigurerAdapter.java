package cn.pursuedream.oauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@EnableWebSecurity
@Configuration
public class MyAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientDetailsService;

    /**
     * 令牌的存储策略: 使用内存存储（为了测试方便）
     */
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

    /**
     * 配置令牌访问的安全约束
     * @param security a fluent configurer for security features
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 开启 /oauth/token_key 验证端口访问权限
                .tokenKeyAccess("permitAll()")
                // 开启 /oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()")
                // 表示支持 client_id 和 client_secret 做登录认证
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端详情
     * @param clients the client details configurer
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 客户端id
                .withClient("hyy001")
                // 客户端密钥
                .secret(passwordEncoder.encode("123456"))
                // 资源id，唯一
                .resourceIds("oauth-resource1")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("all")
                .autoApprove(false)
                .redirectUris("http://www.pursuedream.cn");
    }

    /**
     * 配置令牌访问的端点
     * @param endpoints the endpoints configurer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager)
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }


    /**
     * 授权码模式的service,使用授权码模式authorization_code则必须注入
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        // 测试使用内存存储
        return new InMemoryAuthorizationCodeServices();
    }


    /**
     * 令牌管理服务的配置
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // 客户端配置策略
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        // 支持令牌的刷新
        defaultTokenServices.setSupportRefreshToken(true);
        // 令牌服务
        defaultTokenServices.setTokenStore(tokenStore());
        // access_token的过期时间
        defaultTokenServices.setAccessTokenValiditySeconds(60 * 60);
        // refresh_token的过期时间
        defaultTokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 30);

        return defaultTokenServices;
    }

}

