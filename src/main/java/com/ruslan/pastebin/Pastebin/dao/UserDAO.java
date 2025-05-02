package com.ruslan.pastebin.Pastebin.dao;

import com.ruslan.pastebin.Pastebin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.status = 1")
    List<User> findAllActive();

    @Modifying
    @Query("UPDATE User u SET u.status = 0 WHERE u.id = :userId")
    void block(@Param("userId") Long userId);
}
