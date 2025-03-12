package com.example.agsrdemo.service;

import com.example.agsrdemo.dto.PatientDto;
import com.example.agsrdemo.model.Patient;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public PatientDto mapToPatientDto(Patient patient) {
        if(patient == null) {
            return null;
        }
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setBirthDate(patient.getBirthDate());
        patientDto.setGender(patient.getGender());
        return patientDto;
    }

    public Patient mapToPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setGender(patientDto.getGender());
        return patient;
    }
}
