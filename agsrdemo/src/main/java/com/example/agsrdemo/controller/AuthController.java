package com.example.agsrdemo.controller;

import com.example.agsrdemo.dto.AuthDto;
import com.example.agsrdemo.dto.PatientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Setter
@RestController
@Tag(name = "Authentication controller")
public class AuthController {
    @Value("${client-id}")
    private String clientId;

    @Value("${resource-url}")
    private String resourceServerUrl;

    @Value("${grant-type}")
    private String grantType;

    @PostMapping("/auth")
    @Operation(summary = "Get authentication token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful authorization",
                    content = @Content(schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "401", description = "Wrong credentials",
                    content = @Content)
    })
    public String auth(@Parameter(description = "Authentication data (login and password)", required = true)
                       @RequestBody AuthDto authDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=" + clientId +
                        "&username=" + authDto.getLogin() +
                        "&password=" + authDto.getPassword() +
                        "&grant_type=" + grantType;

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responce = restTemplate.exchange(
                resourceServerUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if(responce.getStatusCode() == HttpStatus.OK) {
            return responce.getBody();
        }
        return null;
    }
}
