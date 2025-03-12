package com.example.agsrdemo.controller;

import com.example.agsrdemo.dto.PatientDto;
import com.example.agsrdemo.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patients controller", description = "API for patient's control")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get all patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patients found",
                    content = @Content(schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "404", description = "Patients not found",
                    content = @Content)
    })
    public ResponseEntity<List<PatientDto>> findAll() {
        final List<PatientDto> patients = patientService.findAll();

        return patients == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find patient by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found",
                    content = @Content(schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "404", description = "Patient not found",
                    content = @Content)
    })
    public ResponseEntity<PatientDto> findById(@Parameter(description = "Patient's id", required = true)
                                               @PathVariable UUID id) {
        final PatientDto patientDto = patientService.findById(id);

        return patientDto == null
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(patientDto, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create new patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient successfully created",
                    content = @Content(schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad input data",
                    content = @Content)
    })
    public ResponseEntity<PatientDto> create(@Parameter(description = "Patient's data", required = true)
                                             @RequestBody PatientDto patientDto) {
        final PatientDto saved = patientService.save(patientDto);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient successfully created",
                    content = @Content(schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad input data",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient to update not found",
                    content = @Content)
    })
    public ResponseEntity<PatientDto> update(@Parameter(description = "Patient's id", required = true)
                                             @PathVariable UUID id,
                                             @Parameter(description = "Patient's data", required = true)
                                             @RequestBody PatientDto patientDto) {
        final PatientDto updated = patientService.update(id, patientDto);

        return updated != null
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete patient using id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient successfully created",
                    content = @Content(schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad id",
                    content = @Content)
    })
    public ResponseEntity<PatientDto> delete(@Parameter(description = "Patient's id", required = true)
                                             @PathVariable UUID id) {
        final PatientDto deleted = patientService.delete(id);

        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
