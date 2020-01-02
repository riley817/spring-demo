package io.tlor.spring.web;

import io.tlor.spring.domain.config.CommonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import({CommonConfiguration.class})
public class ClientWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientWebApplication.class);
    }
}
