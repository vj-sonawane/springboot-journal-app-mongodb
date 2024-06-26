package com.vscode.springbootjournalappmongodb.controller;

import com.vscode.springbootjournalappmongodb.cache.AppCache;
import com.vscode.springbootjournalappmongodb.model.User;
import com.vscode.springbootjournalappmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;


    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAll();
        if(users!=null && !users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.saveAdmin(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/clear-app-cache")
    public String clearCache(){
        appCache.init();
        return "Reinitialising and clearing application cache.";
    }
}
