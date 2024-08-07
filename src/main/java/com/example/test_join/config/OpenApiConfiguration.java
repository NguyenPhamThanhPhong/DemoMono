package com.example.test_join.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("Bsl Cl Service")
                .version("1.0.0")
                .description("Bsl Cl customer service apis specification"));
    }
}
