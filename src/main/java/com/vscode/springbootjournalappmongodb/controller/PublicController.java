package com.vscode.springbootjournalappmongodb.controller;

import com.vscode.springbootjournalappmongodb.model.EmailRequest;
import com.vscode.springbootjournalappmongodb.model.User;
import com.vscode.springbootjournalappmongodb.service.EmailService;
import com.vscode.springbootjournalappmongodb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @GetMapping("/health-check")
    public String healthCheck() {
        return "Health checkup status: Up";
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("send-mail")
    public String sendEmail(@RequestBody EmailRequest emailRequest){
        try {
            emailService.senEmail(emailRequest);
        }catch (Exception ex){
         log.error("PublicController::Exception while sending mail: ",ex);
        }
        return "Email send successfully...!!!";
    }
}
