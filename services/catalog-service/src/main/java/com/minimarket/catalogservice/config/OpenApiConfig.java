package com.minimarket.catalogservice.config;

import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {
    @Bean
    public Content apiErrorContent() {
        return new Content().addMediaType(
                "application/json",
                new MediaType().schema(
                        new Schema<>().$ref("#/components/schemas/ApiError")
                )
        );
    }
}
