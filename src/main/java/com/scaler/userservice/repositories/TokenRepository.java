package com.scaler.userservice.repositories;

import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    // 1. Find by Token Value
    Optional<Token> findByValue(String value);

    // 2. Find by User
    List<Token> findByUser(User user);

    // 3. Find by User and Not Expired
    List<Token> findByUserAndExpiresAfter(User user, Date currentDate);

    // 4. Find by Value and Not Deleted
    Optional<Token> findByValueAndDeletedFalse(String value);

    Optional<Token> findByValueAndDeletedFalseAndExpiresAfter(String value, Date date);

    // 5. Find by User and Deleted Status
    List<Token> findByUserAndDeleted(User user, Boolean deleted);

    // 6. Find by Expiry Date Range
    List<Token> findByExpiresBetween(Date startDate, Date endDate);

    // 7. Find Active Tokens by User (Not Deleted and Not Expired)
    List<Token> findByUserAndDeletedFalseAndExpiresAfter(User user, Date currentDate);

    // 8. Delete by Expiry Date
    void deleteByExpiresBefore(Date currentDate);

    // 9. Delete by User and Deleted Status
    void deleteByUserAndDeleted(User user, Boolean deleted);

    // 10. Count Tokens by User and Deleted Status
    long countByUserAndDeleted(User user, Boolean deleted);
}
