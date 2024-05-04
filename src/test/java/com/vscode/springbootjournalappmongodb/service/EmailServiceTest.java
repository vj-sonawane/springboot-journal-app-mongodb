package com.vscode.springbootjournalappmongodb.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.senEmail("vijaysonawanetech@gmail.com",
                "JavaMailSender Demo Email",
                "Hi Vijay,\n" +
                        "     This is \"Test Email\" using JavaMailSender on 05-May-2024 at 02:11 am.\n\n\n" +
                        "Regards,\n" +
                        "VS-Code");
    }
}
