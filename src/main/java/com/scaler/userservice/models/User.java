package com.scaler.userservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Users")
public class User extends BaseModel {
    @Column(nullable = false)
    private String name;

    private String password;

    @Column(unique = true , nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.PERSIST)
    private List<Role> roles;
}
