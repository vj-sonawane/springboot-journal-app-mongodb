package com.vscode.springbootjournalappmongodb.config;

import com.vscode.springbootjournalappmongodb.model.ConfigJournalApp;
import com.vscode.springbootjournalappmongodb.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;
import java.util.Optional;

@Configuration
public class EmailConfigurationLoader {

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init() {
        Optional<String> pass = configJournalAppRepository
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .filter(password -> "PASSWORD".equals(password.getKey()))
                .map(ConfigJournalApp::getValue)
                .findFirst();

        Optional<String> user = configJournalAppRepository
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .filter(username -> "USER_NAME".equals(username.getKey()))
                .map(ConfigJournalApp::getValue)
                .findFirst();

        if (pass.isPresent() || user.isPresent()) {
            System.out.println("Username from MongoDB: " + user.get());
            System.out.println("Password from MongoDB: " + pass.get());
        }
    }
}
