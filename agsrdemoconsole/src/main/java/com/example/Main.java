package com.example;

import com.example.module.AuthClient;
import com.example.module.Patient;
import com.example.module.PatientApiClient;
import com.example.module.PatientGenerator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String token = AuthClient.getToken();
        for (int i = 0; i < 100; ++i) {
            Patient patient = PatientGenerator.generatePatient();
            System.out.println(patient);
            PatientApiClient.addPatient(patient, token);
        }
    }
}