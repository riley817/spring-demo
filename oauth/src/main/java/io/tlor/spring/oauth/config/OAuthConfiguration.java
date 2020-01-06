package io.tlor.spring.oauth.config;

import io.tlor.spring.oauth.service.PlatformClientDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    @Bean("clientDetailService")
    public ClientDetailsService getPlatformClientDetailService() {
        return new PlatformClientDetailService();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(getPlatformClientDetailService());
    }

    @Bean("accessTokenConverter")
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("tlor.jks"), "tlordev123".toCharArray()).getKeyPair("tlor", "tlordev".toCharArray());
        converter.setKeyPair(keyPair);

        return converter;
    }

    @Bean("tokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

}
