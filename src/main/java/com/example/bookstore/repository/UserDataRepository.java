package com.example.bookstore.repository;
import com.example.bookstore.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * here we are created UserDataRepository interface & extend it with JpaRepository so we can easily interact with database
 * JpaRepository provide inbuilt methods
 */

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    UserData findByUsername(String username);

}
