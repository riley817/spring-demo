package io.tlor.spring.api;

import io.tlor.spring.domain.config.CommonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import(CommonConfiguration.class)
@EnableJpaRepositories(basePackages = {"io.tlor.spring.api.repository", "io.tlor.spring.domain.model"})
@EntityScan(basePackages = {"io.tlor.spring.domain.model.*"})
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class);
    }
}
