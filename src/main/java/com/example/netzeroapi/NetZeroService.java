package com.example.netzeroapi;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

public class NetZeroService {

    static void fixedEstimate(final String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/estimates/fixed/create";
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        try {
            ResponseEntity response = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(null, headers), ResponseEntity.class);
            System.out.println(response.getBody());
        } catch (Exception apiException) {
            System.out.println(apiException.getMessage());
        }
    }

    static void carbonEstimate(final String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/estimates/create-carbon";
        final RestTemplate restTemplate = new RestTemplate();

        final MultiValueMap<String, Object> postBody = new LinkedMultiValueMap<>();
        postBody.add("origin", "Rome");
        postBody.add("destination", "Leeds");
        postBody.add("weight", "0.1");

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String result = restTemplate.postForObject(uri, postBody, String.class, headers);
        System.out.println(result);
    }

    static void listEstimates(final String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/estimates";
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.getForObject(uri, String.class, headers);
        System.out.println(result);
    }

    static void retrieveEstimate(final String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/estimates/:estimate_id";
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.getForObject(uri, String.class, headers);
        System.out.println(result);
    }

    static void cancelEstimate(final String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/estimates/cancel/:estimate_id";
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.getForObject(uri, String.class, headers);
        System.out.println(result);
    }

    static void fixedPurchase(final String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/purchases/fixed/create";
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.postForObject(uri, null, String.class, headers);
        System.out.println(result);
    }

    static void estimateToPurchase(final String apiKey) {

        final String uri = "https://api.net-zero.earth/v1/purchases/create/";
        final RestTemplate restTemplate = new RestTemplate();

        final MultiValueMap<String, Object> postBody = new LinkedMultiValueMap<>();
        postBody.add("estimate", "estimate_id");

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.postForObject(uri, postBody, String.class, headers);
        System.out.println(result);
    }

    static void listPurchases(final String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/purchases";
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.getForObject(uri, String.class, headers);
        System.out.println(result);
    }

    static void retrievePurchase(String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/purchases/:purchase_id";
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.getForObject(uri, String.class, headers);
        System.out.println(result);
    }

    public static void main(String[] args) {
        String apiKey = "api-key:jszlq5bx3gceih0wd78ynvxxpa0mtlhie68cohjt25ykheh82ksn3ll2vndcy9zi";
        fixedEstimate(apiKey);
        carbonEstimate(apiKey);
        listEstimates(apiKey);
        retrieveEstimate(apiKey);
        cancelEstimate(apiKey);
        fixedPurchase(apiKey);
        estimateToPurchase(apiKey);
        listPurchases(apiKey);
        retrievePurchase(apiKey);
    }

}
