package com.scaler.userservice.controllers;

import com.scaler.userservice.models.Role;
import com.scaler.userservice.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsResponse {
    private String username;
    private String password;
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

// Getters and setters
    public static UserDetailsResponse from(User user){
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        if(user != null){
            userDetailsResponse.setUsername(user.getEmail());
            userDetailsResponse.setPassword(user.getPassword());
            userDetailsResponse.setRoles(user.getRoles().stream().map(Role::getValue).collect(Collectors.toList()));
        }
        return userDetailsResponse;
    }
}
