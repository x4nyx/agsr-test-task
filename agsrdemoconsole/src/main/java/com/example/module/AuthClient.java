package com.example.module;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class AuthClient {

    public static String getToken() throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8181/auth");

            httpPost.setHeader("Content-Type", "application/json");

            String json = "{" +
                    "\"login\": \"admin\"," +
                    "\"password\": \"admin\"" +
                    "}";

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String result = EntityUtils.toString(responseEntity);
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode jsonNode = mapper.readTree(result);
                    String accessToken = jsonNode.get("access_token").asText();
                    return accessToken;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}