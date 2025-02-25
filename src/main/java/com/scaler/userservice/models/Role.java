package com.scaler.userservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "Roles")
@NoArgsConstructor
public class Role extends BaseModel {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(value, role.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public Role(String value) {
        this.value = value;
    }

    @Column(nullable = false , unique = true )
    private String value;

}
