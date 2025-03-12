package com.example.agsrdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private static final String[] SWAGGER_PATHS = {"/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/webjars/swagger-ui/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/auth").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/patients").hasAnyRole("Patient", "Practitioner")
                                .requestMatchers(HttpMethod.GET, "/api/patients/{id}").hasAnyRole("Patient", "Practitioner")
                                .requestMatchers(HttpMethod.POST, "/api/patients").hasRole("Practitioner")
                                .requestMatchers(HttpMethod.PUT, "/api/patients/{id}").hasRole("Practitioner")
                                .requestMatchers(HttpMethod.DELETE, "/api/patients/{id}").hasRole("Practitioner")
                                .requestMatchers(SWAGGER_PATHS).permitAll()
                                .anyRequest().authenticated()
                ).sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).oauth2ResourceServer(
                        resourceServer -> resourceServer.jwt(
                                jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
                                    keycloakAuthConverter()
                                )
                        )
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> keycloakAuthConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(
                new AuthoritiesConverter()
        );
        return converter;
    }
}
