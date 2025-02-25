package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.LoginRequestDTO;
import com.scaler.userservice.dtos.SignUpRequestDTO;
import com.scaler.userservice.dtos.UserINDTO;
import com.scaler.userservice.dtos.UserOutDTO;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PatchMapping("/{userId}")
    ResponseEntity<UserOutDTO> updateUser(@PathVariable Long userId, @RequestBody UserINDTO userINDTO) {
        User user = userService.updateUser(userId , userINDTO);
        return ResponseEntity.ok(UserOutDTO.from(user));
    }
    @GetMapping("/{userId}")
    ResponseEntity<UserOutDTO> getUserById(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(UserOutDTO.from(user));
    }

    @GetMapping("/")
    ResponseEntity<List<UserOutDTO>> getAllUsers() {
        List<User> all = userService.findAll();
        List<UserOutDTO> response = new ArrayList<>();
        for (User user : all) {
            response.add(UserOutDTO.from(user));
        }
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/signup")
//    ResponseEntity<UserOutDTO> signUp(@RequestBody  SignUpRequestDTO signUpRequestDTO){
//        UserOutDTO userOutDTO = userService.signUp(signUpRequestDTO);
//        return ResponseEntity.ok(userOutDTO);
//    }
//
//    @PostMapping("/login")
//    ResponseEntity<Token> logIn(@RequestBody LoginRequestDTO loginRequestDTO){
//        Token token = userService.logIn(loginRequestDTO);
//        return ResponseEntity.ok(token);
//    }
//
//    @GetMapping("/login/{tokenValue}")
//    ResponseEntity<Token> validate(@PathVariable("tokenValue") String tokenValue){
//        Token token = userService.validate(tokenValue);
//        return ResponseEntity.ok(token);
//    }
//
//    @GetMapping("/logout/{tokenValue}")
//    ResponseEntity<String> logOut(@PathVariable("tokenValue") String tokenValue ){
//        Boolean b = userService.logOut(tokenValue);
//        if(b){
//            return ResponseEntity.ok("LoggedOut Successfully");
//        }
//        return ResponseEntity.badRequest().body("Redirect to Login page");
//
//    }


}
