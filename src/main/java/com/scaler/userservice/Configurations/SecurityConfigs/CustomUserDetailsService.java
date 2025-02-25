package com.scaler.userservice.Configurations.SecurityConfigs;



import com.scaler.userservice.dtos.SignUpRequestDTO;
import com.scaler.userservice.models.Role;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map( role -> new SimpleGrantedAuthority("ROLE_"+ role.getValue())).collect(Collectors.toList());
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username)
                .password(user.getPassword())
                .authorities(authorities);

        return userBuilder.build();

    }

    @Transactional
    public User registerNewUser(SignUpRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username already taken");
        }

        User user = new User();
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());

        return userRepository.save(user);
    }
}
