package com.vscode.springbootjournalappmongodb.service;

import com.vscode.springbootjournalappmongodb.cache.AppCache;
import com.vscode.springbootjournalappmongodb.model.EmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;
    @Autowired
    private JavaMailSender javaMailSender;

    public void senEmail(EmailRequest emailRequest){
        System.out.println("Username from Environment variable: "+username);
        System.out.println("Password from Environment variable: "+password);
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(emailRequest.getTo());
            mail.setSubject(emailRequest.getSubject());
            mail.setText(emailRequest.getBody());
            javaMailSender.send(mail);
        }catch (Exception ex){
            log.error("EmailService::Exception while sending email: ",ex);
        }
    }


}
