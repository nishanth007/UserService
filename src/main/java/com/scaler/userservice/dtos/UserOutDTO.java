package com.scaler.userservice.dtos;

import com.scaler.userservice.models.Role;
import com.scaler.userservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOutDTO {
    private String name;
    private String email;
    private List<Role> roles;

    public static UserOutDTO from(User user) {
        if(user == null) return null;

        UserOutDTO userOutDTO = new UserOutDTO();
        userOutDTO.name = user.getName();
        userOutDTO.email = user.getEmail();
        userOutDTO.roles = user.getRoles();

        return userOutDTO;
    }
}
