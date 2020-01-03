package io.tlor.spring.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

@Configuration
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    @Bean("clientDetailService")
    public ClientDetailsService getPlatformClientDetailService() {
        return new PlatformClientDetailService();
    }




}
