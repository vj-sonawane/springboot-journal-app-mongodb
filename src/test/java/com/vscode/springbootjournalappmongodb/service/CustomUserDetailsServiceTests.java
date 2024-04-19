package com.vscode.springbootjournalappmongodb.service;

import com.vscode.springbootjournalappmongodb.model.JournalEntry;
import com.vscode.springbootjournalappmongodb.model.User;
import com.vscode.springbootjournalappmongodb.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class CustomUserDetailsServiceTests {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private UserRepository userRepository;


    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(User.builder()
                        .userName("Ashwini")
                        .password("password")
                        .journalEntry(List.of(JournalEntry.builder()
                                .title("Mokito")
                                .content("JournalEntryUsingMokito")
                                .date(LocalDateTime.now())
                                .build()))
                        .roles(List.of("User","Admin"))
                        .build());

        UserDetails user = customUserDetailsService.loadUserByUsername("Vijay");
        Assertions.assertNotNull(user);
    }

}
