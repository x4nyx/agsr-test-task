package com.example.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class PatientApiClient {
    private static final String API_URL = "http://localhost:8181/api/patients";

    public static void addPatient(Patient patient, String token) throws IOException {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(API_URL);

            ObjectMapper mapper = new ObjectMapper();

            ObjectNode jsonObject = mapper.createObjectNode();

            jsonObject.put("name", patient.getName());
            jsonObject.put("gender", patient.getGender());
            jsonObject.put("birthDate", String.valueOf(patient.getBirthDay()));

            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);

            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + token);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                System.out.println("Response code:" + response.getCode());
                System.out.println("Response body:" + response.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
