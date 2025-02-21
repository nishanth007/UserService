package com.scaler.userservice.services;

import com.scaler.userservice.dtos.LoginRequestDTO;
import com.scaler.userservice.dtos.SignUpRequestDTO;
import com.scaler.userservice.dtos.UserOutDTO;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;

public interface UserService {

    User findByEmail(String email);

    UserOutDTO signUp(SignUpRequestDTO signUpRequestDTO);

    Token logIn(LoginRequestDTO loginRequestDTO);

    Token validate(String tokenValue);

    Boolean logOut(String tokenValue );
}
