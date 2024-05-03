package com.vscode.springbootjournalappmongodb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vscode.springbootjournalappmongodb.api.response.ProductResponse;
import com.vscode.springbootjournalappmongodb.model.FakeUser;
import com.vscode.springbootjournalappmongodb.model.User;
import com.vscode.springbootjournalappmongodb.service.FakeStoreApiService;
import com.vscode.springbootjournalappmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private FakeStoreApiService fakeStoreApiService;


    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userService.saveNewUser(userInDB);
        return new ResponseEntity<>(userInDB, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUserByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String products = fakeStoreApiService.getProducts();
        return new ResponseEntity<>("Hi " + authentication.getName() + "products are :" + products, HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createFakeUser(@RequestBody FakeUser fakeUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = fakeStoreApiService.saveUser(fakeUser);
        return new ResponseEntity<>("Hi " + authentication.getName() + " Your Fake User Id :" + userId, HttpStatus.OK);
    }
}
