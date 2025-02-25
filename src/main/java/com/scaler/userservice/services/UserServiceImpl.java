package com.scaler.userservice.services;

import com.scaler.userservice.dtos.LoginRequestDTO;
import com.scaler.userservice.dtos.SignUpRequestDTO;
import com.scaler.userservice.dtos.UserINDTO;
import com.scaler.userservice.dtos.UserOutDTO;
import com.scaler.userservice.models.Role;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.RoleRepository;
import com.scaler.userservice.repositories.TokenRepository;
import com.scaler.userservice.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    Set<Role> returnRoleObjects(List<Role> roles) {
        Set<Role> newRoles = new HashSet<Role>();
        for(Role role : roles) {
            Optional<Role> r = roleRepository.findByValue(role.getValue());
            if(r.isPresent()) {
                newRoles.add(r.get());
            }
            else{
                newRoles.add(roleRepository.save(role));
            }
        }
        return newRoles;
    }
    @Override
    @Transactional
    public User updateUser(Long userId , UserINDTO userINDTO) {
        User user = userRepository.findById(userId).orElse(null);
        if(user != null) {
            if(userINDTO.getEmail()!=null) user.setEmail(userINDTO.getEmail());
            if(userINDTO.getName()!=null) user.setName(userINDTO.getName());

            Set<Role> rolesFromPayload    = returnRoleObjects(userINDTO.getRoles());
//            List<Role> existingRolesFromDB = user.getRoles();
//            rolesFromPayload.addAll(existingRolesFromDB);

            if(userINDTO.getRoles()!=null) {
                user.getRoles().clear();
                user.getRoles().addAll(rolesFromPayload);
            }
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUserRoles(UserINDTO user) {
        return null;
    }














//    @Override
//    public UserOutDTO signUp(SignUpRequestDTO signUpRequestDTO) {
//        User byEmail = findByEmail(signUpRequestDTO.getEmail());
//        if (byEmail != null) {
//            return UserOutDTO.from(byEmail);
//        }
//        User user = new User();
//        user.setEmail(signUpRequestDTO.getEmail());
//        user.setName(signUpRequestDTO.getName());
//        user.setRoles(List.of(new Role("INSTRUCTOR")));
//        //TODO Encryption of password
//        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
//        user = userRepository.save(user);
//
//        return UserOutDTO.from(user);
//    }
//
//    @Override
//    public Token logIn(LoginRequestDTO loginRequestDTO) {
//        Token token = new Token();
//        User byEmail = findByEmail(loginRequestDTO.getEmail());
//        if(byEmail == null) {
//            throw new RuntimeException("User not found");
//        }
//        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), byEmail.getPassword())) {
//            throw new RuntimeException("Wrong password");
//        }
//        token.setUser(byEmail);
//        // Current date
//        Date today = new Date();
//
//        // Use Calendar to add days
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(today);
//
//        // Add x days (e.g., 7 days)
//        int daysToAdd = 7;
//        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
//
//        // Get the updated date
//        Date futureDate = calendar.getTime();
//
//        token.setExpires(futureDate);
//        token.setValue(UUID.randomUUID().toString());
//
//        return tokenRepository.save(token);
//    }
//
//    @Override
//    public Token validate(String tokenValue) {
//        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedFalseAndExpiresAfter(tokenValue,new Date());
//        if (optionalToken.isEmpty()) {
//            throw new RuntimeException("Token not found");
//        }
//
//        return optionalToken.get();
//    }
//
//    @Override
//    public Boolean logOut(String tokenValue) {
//        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedFalse(tokenValue);
//
//        if (optionalToken.isEmpty()) {
//            return Boolean.FALSE;
//        }
//
//        Token token = optionalToken.get();
//        token.setDeleted(true);
//        tokenRepository.save(token);
//
//        return Boolean.TRUE;
//    }
}
