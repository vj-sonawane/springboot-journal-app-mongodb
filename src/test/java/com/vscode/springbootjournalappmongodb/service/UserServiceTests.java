package com.vscode.springbootjournalappmongodb.service;

import com.vscode.springbootjournalappmongodb.model.User;
import com.vscode.springbootjournalappmongodb.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTests {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
/*
    @BeforeAll
    @BeforeEach
    @AfterAll
    @AfterEach
*/
    @ParameterizedTest
    @CsvSource({
            "Vijay",
            "Swara",
            "Samarth"

    })
    void TestFindByUserName(String userName){
        assertNotNull(userRepository.findByUserName(userName),"Failed For: " +userName);
    }


    @Test
    void TestFindUserJournalEntry(){
        User user = userRepository.findByUserName("Vijay");
        assertFalse(user.getJournalEntry().isEmpty());
    }

    @Disabled("To check the @Disabled annotation Junit4 uses @Ignore for the same")
    @ParameterizedTest
    @CsvSource({
            "5,5,10",
            "8,2,10",
            "9,1,10",
            "6,4,10",
            "7,3,10"
    })
    void parametrisedTests(int num1, int num2, int excepted){
        assertEquals(excepted, num1+num2);
    }

}
