package com.example.demo.servico;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getJSON(String url) {
        try {
            return this.restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}