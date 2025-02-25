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
public class UserINDTO {
    private String name;
    private String email;
    private List<Role> roles;

    public static UserINDTO from(User user) {
        if(user == null) return null;

        UserINDTO userINDTO = new UserINDTO();
        userINDTO.name = user.getName();
        userINDTO.email = user.getEmail();
        userINDTO.roles = user.getRoles();

        return userINDTO;
    }
}
