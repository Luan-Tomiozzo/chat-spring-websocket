package com.teste.demo.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi grupo() {
        return GroupedOpenApi.builder()
                .group("batepapo")
                .pathsToMatch("/**")
                .build();
    }

    private Info apiInfo() {
        return new Info()
                .title("API de chat")
                .description("API para envio e recebimento de mensagens")
                .version("1.0.0");
    }
}
