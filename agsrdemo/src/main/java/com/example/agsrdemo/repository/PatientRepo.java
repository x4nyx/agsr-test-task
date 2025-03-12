package com.example.agsrdemo.repository;

import com.example.agsrdemo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepo extends JpaRepository<Patient, UUID> {
}
