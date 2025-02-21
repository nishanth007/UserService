package com.scaler.userservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseModel {
    @Column(nullable = false)
    private String value;
}
