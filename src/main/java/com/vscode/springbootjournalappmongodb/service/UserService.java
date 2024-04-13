package com.vscode.springbootjournalappmongodb.service;

import com.vscode.springbootjournalappmongodb.model.User;
import com.vscode.springbootjournalappmongodb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        log.info("UserService::saveUser RequestBody{}", user);
        userRepository.save(user);
        log.info("user saved successfully");
    }

    public void saveNewUser(User user) {
        log.debug("UserService::saveNewUser UserRequestBody {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepository.save(user);
        log.info("user saved successfully with encoded password");
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void deleteUserByUserName(String userName) {
        log.debug("UserService::deleteUserByUserName userName: {}", userName);
        userRepository.deleteByUserName(userName);
    }
}
