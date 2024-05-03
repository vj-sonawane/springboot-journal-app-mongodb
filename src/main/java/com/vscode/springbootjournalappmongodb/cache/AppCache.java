package com.vscode.springbootjournalappmongodb.cache;

import com.vscode.springbootjournalappmongodb.model.ConfigJournalApp;
import com.vscode.springbootjournalappmongodb.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AppCache {

    public enum keys {
        POST_API,
        GET_API,
        USER_NAME,
        PASSWORD,
        API_KEY,
        JWT_TOKEN,
    }


    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;


    public Map<String,String> appCache;

    @Scheduled(cron = "*/5 * * * *")
    @PostConstruct
    public void init(){
        appCache = new ConcurrentHashMap<>();
        List<ConfigJournalApp> configs = configJournalAppRepository.findAll();
        for(ConfigJournalApp configEntity: configs){
            appCache.put(configEntity.getKey(),configEntity.getValue());
        }
     }
}
