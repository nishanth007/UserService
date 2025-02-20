package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.LoginRequestDTO;
import com.scaler.userservice.dtos.SignUpRequestDTO;
import com.scaler.userservice.dtos.UserOutDTO;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    ResponseEntity<UserOutDTO> signUp(@RequestBody  SignUpRequestDTO signUpRequestDTO){
        UserOutDTO userOutDTO = userService.signUp(signUpRequestDTO);
        return ResponseEntity.ok(userOutDTO);
    }

    @PostMapping("/login")
    ResponseEntity<Token> logIn(@RequestBody LoginRequestDTO loginRequestDTO){
        Token token = userService.logIn(loginRequestDTO);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/login/{tokenValue}")
    ResponseEntity<String> validate(@PathVariable("tokenValue") String tokenValue){
        Boolean validate = userService.validate(tokenValue);
        if(validate){
            return ResponseEntity.ok("Token is valid");
        }
        return ResponseEntity.ok("Invalid Token Redirected to Login");
    }

    @GetMapping("/logout/{tokenValue}")
    ResponseEntity<String> logOut(@PathVariable("tokenValue") String tokenValue ){
        Boolean b = userService.logOut(tokenValue);
        if(b){
            return ResponseEntity.ok("LoggedOut Successfully");
        }
        return ResponseEntity.badRequest().body("Redirect to Login page");

    }
}
