package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.CommentDAO;
import com.ruslan.pastebin.Pastebin.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    public CommentDAO commentDAO;

    @GetMapping("/comment")
    public List<Comment> getAllComments(){
        return commentDAO.getAll();
    }
    @GetMapping("/comment/{id}")
    public Comment getCommentById(@PathVariable int id){
        return commentDAO.getById(id);
    }
    @PostMapping("/comment")
    public Comment saveComment(@RequestBody Comment comment){
        Comment newComment = commentDAO.save(comment);
        return newComment;
    }
    @PutMapping("/comment/{id}")
    public Comment updateComment(@PathVariable Long id,@RequestBody Comment comment){
        comment.setId(id);
        Comment updatedComment = commentDAO.update(comment);
        return updatedComment;
    }
    @DeleteMapping("/comment")
    public void deleteComment(@PathVariable int id){
        commentDAO.deleteById(id);
    }
}
