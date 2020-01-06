package io.tlor.spring.oauth;

import io.tlor.spring.domain.config.OAuthDataBaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@Import(OAuthDataBaseConfig.class)
@EnableJpaRepositories(basePackages = {"io.tlor.spring.oauth.repository", "io.tlor.spring.domain.model.oauth"})
@EntityScan(basePackages = {"io.tlor.spring.domain.model.oauth"})
@EnableAuthorizationServer
public class OAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class);
    }
}
