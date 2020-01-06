package io.tlor.spring.oauth.config;

import io.tlor.spring.oauth.service.PlatformClientDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    @Bean("clientDetailService")
    public ClientDetailsService getPlatformClientDetailService() {
        return new PlatformClientDetailService();
    }
}
