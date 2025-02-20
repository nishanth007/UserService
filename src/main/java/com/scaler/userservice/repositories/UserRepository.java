package com.scaler.userservice.repositories;

import com.scaler.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    // 2. Find by Email
    Optional<User> findByEmail(String email);

    // 3. Find by Name containing substring (for search functionality)
    List<User> findByNameContainingIgnoreCase(String name);


    // 5. Check if Email Exists (For registration validation)
    boolean existsByEmail(String email);

    // 6. Delete by Email
    void deleteByEmail(String email);
}
