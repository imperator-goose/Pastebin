package com.ruslan.pastebin.Pastebin.dao;

import com.ruslan.pastebin.Pastebin.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Long> {
    @Modifying
    @Query("UPDATE Comment c SET c.status = 0 WHERE c.id = :commentId")
    void block(@Param("commentId") Long commentId);
}
