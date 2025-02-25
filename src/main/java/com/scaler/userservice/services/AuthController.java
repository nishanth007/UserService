package com.scaler.userservice.services;

import com.scaler.userservice.Configurations.SecurityConfigs.CustomAuthenticationFilter;
import com.scaler.userservice.Configurations.SecurityConfigs.CustomUserDetailsService;
import com.scaler.userservice.Configurations.SecurityConfigs.TokenUtil;
import com.scaler.userservice.dtos.LoginRequestDTO;
import com.scaler.userservice.dtos.SignUpRequestDTO;
import com.scaler.userservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Validated @RequestBody SignUpRequestDTO request) {
        User registeredUser = userDetailsService.registerNewUser(request);
        return ResponseEntity.ok("User registered successfully with username: " + registeredUser.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenUtil.generateToken(request.getEmail());
        return ResponseEntity.ok("Bearer " + token);
    }

    @GetMapping("/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            return "Hello, Authenticated User! Welcome " + principal.getUsername();
        }


        return "Hello, Authenticated User!";
    }
}
