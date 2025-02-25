package com.scaler.userservice.dtos;

import com.scaler.userservice.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    String name;
    String email;
    String password;
    List<Role> roles;
}
