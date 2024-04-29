package com.vscode.springbootjournalappmongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class FakeStoreApiService {

    private static final String API = "https://fakestoreapi.com/products";

    private static final Logger logger = Logger.getLogger(FakeStoreApiService.class.getName());


    @Autowired
    private RestTemplate restTemplate;


    public String getProducts() {
        ResponseEntity<String> exchange = restTemplate.exchange(API, HttpMethod.GET, null, String.class);
        logger.info("Products of FakeStoreAPI: "+exchange.getBody());
        return exchange.getBody();
    }
}
