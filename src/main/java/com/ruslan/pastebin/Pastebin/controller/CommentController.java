package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.CommentDAO;
import com.ruslan.pastebin.Pastebin.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    public CommentDAO commentDAO;

    @GetMapping("/comment")
    public List<Comment> getAllComments(){
        return commentDAO.findAll();
    }
    @GetMapping("/comment/{id}")
    public Optional<Comment> getCommentById(@PathVariable Long id){
        return commentDAO.findById(id);
    }
    @PostMapping("/comment")
    public Comment saveComment(@RequestBody Comment comment){
        Comment newComment = commentDAO.save(comment);
        return newComment;
    }
    @PutMapping("/comment/{id}")
    public Comment updateComment(@PathVariable Long id,@RequestBody Comment comment){
        comment.setId(id);
        Comment updatedComment = commentDAO.save(comment);
        return updatedComment;
    }
    @DeleteMapping("/comment")
    public void deleteComment(@PathVariable Long id){
        commentDAO.deleteById(id);
    }
}
