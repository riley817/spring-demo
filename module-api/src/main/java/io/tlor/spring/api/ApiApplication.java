package io.tlor.spring.api;

import io.tlor.spring.common.config.CommonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CommonConfiguration.class)
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class);
    }
}
