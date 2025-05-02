package com.ruslan.pastebin.Pastebin.dao;

import com.ruslan.pastebin.Pastebin.entity.Post;
import com.ruslan.pastebin.Pastebin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostDAO extends JpaRepository<Post, Long> {
    @Query("SELECT u FROM User u WHERE u.status = 1")
    List<User> findAllActive();

    @Modifying
    @Query("UPDATE Post p SET p.status = 0 WHERE p.id = :postId")
    void block(@Param("postId") Long postId);
}
