package com.example.agsrdemo.service;

import com.example.agsrdemo.dto.PatientDto;
import com.example.agsrdemo.model.Patient;
import com.example.agsrdemo.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepo patientRepo;
    private final MappingUtils mappingUtils;

    @Autowired
    public PatientService(PatientRepo patientRepo, MappingUtils mappingUtils) {
        this.patientRepo = patientRepo;
        this.mappingUtils = mappingUtils;
    }

    @PreAuthorize("hasAuthority('Patient.Read')")
    public PatientDto findById(UUID id) {
        return mappingUtils.mapToPatientDto(patientRepo.findById(id).orElse(null));
    }

    @PreAuthorize("hasAuthority('Patient.Read')")
    public List<PatientDto> findAll() {
        return patientRepo.findAll().stream()
                .map(mappingUtils::mapToPatientDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('Patient.Write')")
    public PatientDto save(PatientDto patientDto) {
        Patient patient = mappingUtils.mapToPatient(patientDto);
        Patient savedPatient = patientRepo.save(patient);
        return mappingUtils.mapToPatientDto(savedPatient);
    }

    @PreAuthorize("hasAuthority('Patient.Write')")
    public PatientDto update(UUID id, PatientDto patientDto) {
        Patient patient = patientRepo.findById(id).orElse(null);
        if (patient == null) {
            return null;
        }
        patientDto.setId(id);
        Patient updatedPatient = mappingUtils.mapToPatient(patientDto);
        return mappingUtils.mapToPatientDto(patientRepo.save(updatedPatient));
    }

    @PreAuthorize("hasAuthority('Patient.Delete')")
    public PatientDto delete(UUID id) {
        PatientDto patientDto = mappingUtils.mapToPatientDto(patientRepo.findById(id).orElse(new Patient()));
        patientRepo.deleteById(id);
        return patientDto;
    }
}
