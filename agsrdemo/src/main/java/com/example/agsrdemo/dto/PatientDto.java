package com.example.agsrdemo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PatientDto {
    private UUID id;
    private String name;
    private String gender;
    private LocalDateTime birthDate;
}
