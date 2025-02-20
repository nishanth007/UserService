package com.scaler.userservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Roles")
public class Role extends BaseModel {
    @Column(nullable = false)
    private String value;
}
