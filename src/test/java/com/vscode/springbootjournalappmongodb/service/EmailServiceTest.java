package com.vscode.springbootjournalappmongodb.service;

import com.vscode.springbootjournalappmongodb.model.EmailRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.senEmail(EmailRequest.builder()
                .to("vijaysonawanetech@gmail.com")
                .subject("Test mail using JavaMailSender")
                .body("Test mail using Dummy Object of Email request")
                .build());
    }
}
