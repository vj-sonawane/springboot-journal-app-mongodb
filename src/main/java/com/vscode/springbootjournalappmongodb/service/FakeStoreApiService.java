package com.vscode.springbootjournalappmongodb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vscode.springbootjournalappmongodb.api.response.ProductResponse;
import com.vscode.springbootjournalappmongodb.model.FakeUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class FakeStoreApiService {

    private static final String GET_API = "https://fakestoreapi.com/products";

    @Value("${POST_API}")
    private String postAPI;

    @Autowired
    private RestTemplate restTemplate;


    public String getProducts() {
        ResponseEntity<String> exchange = restTemplate.exchange(GET_API, HttpMethod.GET, null, String.class);
        log.debug("FakeStoreApiService::getProducts GET Products : {} ",exchange.getBody());
        return exchange.getBody();
        }

    public String saveUser(FakeUser fakeUser) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        log.debug("RequestBody::FakeUser: {} URL: {} ",fakeUser.toString(),postAPI);
        HttpEntity<FakeUser> userEntity = new HttpEntity<>(fakeUser);
        ResponseEntity<String> id = restTemplate.exchange(postAPI, HttpMethod.POST, userEntity, String.class);
        String jsonBeautify = mapper.writeValueAsString(id);
        log.debug("FakeStoreApiService::saveUser POST FakeUser Id: {} ",jsonBeautify);
        return id.getBody();
    }
}
