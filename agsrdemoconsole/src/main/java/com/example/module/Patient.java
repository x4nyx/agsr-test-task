package com.example.module;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Patient {
    private UUID id;
    private String name;
    private String gender;
    private LocalDateTime birthDay;
}
