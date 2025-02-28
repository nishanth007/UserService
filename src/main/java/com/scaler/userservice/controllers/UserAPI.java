package com.scaler.userservice.controllers;

import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserAPI {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    UserDetailsResponse loadByUserName(@RequestParam String username){
        User user = userRepository.findByEmail(username).orElseThrow();
        return UserDetailsResponse.from(user);
    }


}
