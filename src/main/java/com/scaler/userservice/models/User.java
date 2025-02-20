package com.scaler.userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "Users")
public class User extends BaseModel {
    @Column(nullable = false)
    private String name;

    private String password;

    @Column(unique = true , nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
