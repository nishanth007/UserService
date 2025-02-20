package com.scaler.userservice.services;

import com.scaler.userservice.dtos.LoginRequestDTO;
import com.scaler.userservice.dtos.SignUpRequestDTO;
import com.scaler.userservice.dtos.UserOutDTO;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.TokenRepository;
import com.scaler.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;
    @Override

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    @Override
    public UserOutDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        User user = new User();
        user.setEmail(signUpRequestDTO.getEmail());
        user.setName(signUpRequestDTO.getName());
        user.setPassword(signUpRequestDTO.getPassword());
        user = userRepository.save(user);

        return UserOutDTO.from(user);
    }

    @Override
    public Token logIn(LoginRequestDTO loginRequestDTO) {
        Token token = new Token();
        User byEmail = findByEmail(loginRequestDTO.getEmail());
        if(byEmail == null) {
            throw new RuntimeException("User not found");
        }
        token.setUser(byEmail);
        // Current date
        Date today = new Date();

        // Use Calendar to add days
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        // Add x days (e.g., 7 days)
        int daysToAdd = 7;
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

        // Get the updated date
        Date futureDate = calendar.getTime();

        token.setExpires(futureDate);
        token.setValue(UUID.randomUUID().toString());

        return tokenRepository.save(token);
    }

    @Override
    public Boolean validate(String tokenValue) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedFalseAndExpiresAfter(tokenValue,new Date());
        if (optionalToken.isEmpty()) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean logOut(String tokenValue) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedFalse(tokenValue);

        if (optionalToken.isEmpty()) {
            return Boolean.FALSE;
        }

        Token token = optionalToken.get();
        token.setDeleted(true);
        tokenRepository.save(token);

        return Boolean.TRUE;
    }
}
