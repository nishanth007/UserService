package com.scaler.userservice.repositories;

import com.scaler.userservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    Optional<Role> findById(Long aLong);

    Optional<Role>findByValue(String value);
}
