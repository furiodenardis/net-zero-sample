package com.example.netzeroapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class NetZeroService {

    static String fixedEstimate(final String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/estimates/fixed/create";
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final HttpEntity request = new HttpEntity(null, headers);

        String response = restTemplate.postForObject(uri, request, String.class);
        System.out.println(response);

        Gson gson = new Gson();
        Map responseMap = gson.fromJson(response, Map.class);
        return (String) responseMap.get("id");
    }

    static void carbonEstimate(final String apiKey)  throws JsonProcessingException {
        final String uri = "https://api.net-zero.earth/v1/estimates/create-carbon";
        final RestTemplate restTemplate = new RestTemplate();

        HashMap<String, String> postBodyMap = new HashMap<>();
        postBodyMap.put("origin", "LS27 8DX");
        postBodyMap.put("destination", "E1 6AN");
        postBodyMap.put("weight", "1");

        String postBody = new ObjectMapper().writeValueAsString(postBodyMap);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final HttpEntity request = new HttpEntity(postBody, headers);
        String result = restTemplate.postForObject(uri, request, String.class, headers);
        System.out.println(result);
    }

    static void listEstimates(final String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/estimates/";
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.getForObject(uri, String.class, headers);
        System.out.println(result);
    }

    static void retrieveEstimate(final String apiKey, final String estimateId) {
        final String uri = "https://api.net-zero.earth/v1/estimates/" + estimateId;
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.getForObject(uri, String.class, headers);
        System.out.println(result);
    }

    static void cancelEstimate(final String apiKey, final String estimateId)  {
        final String uri = "https://api.net-zero.earth/v1/estimates/cancel/" + estimateId;
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.getForObject(uri, String.class, headers);
        System.out.println(result);
    }

    static String fixedPurchase(final String apiKey) {
        final String uri = "https://api.net-zero.earth/v1/purchases/fixed/create";
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        final HttpEntity request = new HttpEntity(null, headers);

        final String response = restTemplate.postForObject(uri, request, String.class);
        System.out.println(response);

        Gson gson = new Gson();
        Map responseMap = gson.fromJson(response, Map.class);
        return (String) responseMap.get("id");

    }

    static void estimateToPurchase(final String apiKey, final String estimateId) throws JsonProcessingException {

        final String uri = "https://api.net-zero.earth/v1/purchases/create/";
        final RestTemplate restTemplate = new RestTemplate();

        final HashMap<String, String> postBodyMap = new HashMap<>();
        postBodyMap.put("estimate", estimateId);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String postBody = new ObjectMapper().writeValueAsString(postBodyMap);
        final HttpEntity request = new HttpEntity(postBody, headers);

        final String result = restTemplate.postForObject(uri, request, String.class);
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

    static void retrievePurchase(final String apiKey, final String purchaseId) {
        final String uri = "https://api.net-zero.earth/v1/purchases/" + purchaseId;
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        final String result = restTemplate.getForObject(uri, String.class, headers);
        System.out.println(result);
    }

    public static void main(String[] args) throws JsonProcessingException {
        String apiKey = "api-key:supsgsh1qugqlirjb14sob7se543pc878imrqamgfnsknogomj0y4jm4srm2q3c7";
        final String estimateId = fixedEstimate(apiKey);
        carbonEstimate(apiKey);
//        listEstimates(apiKey);
//        retrieveEstimate(apiKey, estimateId);
        estimateToPurchase(apiKey, estimateId);
        cancelEstimate(apiKey, estimateId);
        final String purchaseId = fixedPurchase(apiKey);
        listPurchases(apiKey);
//        retrievePurchase(apiKey);
    }

}
