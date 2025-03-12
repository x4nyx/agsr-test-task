package com.example.agsrdemo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String OAUTH_SCHEME_BEARER = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AGSR API")
                        .version("1.0")
                        .description("demo"))
                .addSecurityItem(new SecurityRequirement()
                        .addList(OAUTH_SCHEME_BEARER))
                .components(new Components()
                        .addSecuritySchemes(OAUTH_SCHEME_BEARER, new SecurityScheme()
                                .name(OAUTH_SCHEME_BEARER)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
