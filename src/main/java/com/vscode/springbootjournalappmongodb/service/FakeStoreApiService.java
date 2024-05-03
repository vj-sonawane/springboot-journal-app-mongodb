package com.vscode.springbootjournalappmongodb.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vscode.springbootjournalappmongodb.cache.AppCache;
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

    @Autowired
    private AppCache cache;

    @Value("${POST_API}")
    private String postAPI;

    @Autowired
    private RestTemplate restTemplate;


    public String getProducts() {
        String getApi = cache.appCache.get(AppCache.keys.GET_API.toString());
        log.debug("GET_API from cache memory: {}",getApi);
        ResponseEntity<String> exchange = restTemplate.exchange(getApi, HttpMethod.GET, null, String.class);
        log.debug("FakeStoreApiService::getProducts GET Products : {} ",exchange.getBody());
        return exchange.getBody();
        }

    public String saveUser(FakeUser fakeUser) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonUser = gson.toJson(fakeUser);
        log.debug("RequestBody::FakeUser: {} ",jsonUser);
        log.debug("POST_URL from application.properties: {} ",postAPI);
        log.info("POST_API from DB: {} ",cache.appCache.get(AppCache.keys.POST_API.toString()));
        log.info("GET_API from DB: {} ",cache.appCache.get(AppCache.keys.GET_API.toString()));
        log.info("USER_NAME from DB: {} ",cache.appCache.get(AppCache.keys.USER_NAME.toString()));
        log.info("PASSWORD from DB: {} ",cache.appCache.get(AppCache.keys.PASSWORD.toString()));
        log.info("API_KEY from DB: {} ",cache.appCache.get(AppCache.keys.API_KEY.toString()));
        log.info("JWT_TOKEN from DB: {} ",cache.appCache.get(AppCache.keys.JWT_TOKEN.toString()));
        HttpEntity<FakeUser> userEntity = new HttpEntity<>(fakeUser);
        ResponseEntity<String> id = restTemplate.exchange(postAPI, HttpMethod.POST, userEntity, String.class);
        log.debug("FakeStoreApiService::saveUser POST FakeUser Id: {} ",id);
        return id.getBody();
    }
}
