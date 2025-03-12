package com.example.module;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class PatientGenerator {
    private static final Faker faker = new Faker();

    private static final String[] GENDER = {"male", "female"};

    private static LocalDateTime generateRandomDateTime() {
        Date randomDate = faker.date().between(
                new Date(System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000),
                new Date()
        );

        return randomDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Patient generatePatient() {
        Patient patient = new Patient();
        patient.setName(faker.name().fullName());
        patient.setBirthDay(generateRandomDateTime());
        patient.setGender(faker.options().nextElement(GENDER));

        return patient;
    }
}
