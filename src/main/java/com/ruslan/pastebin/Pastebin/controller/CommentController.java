package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.CommentDAO;
import com.ruslan.pastebin.Pastebin.entity.Comment;
import com.ruslan.pastebin.Pastebin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    public CommentService commentService;

    @GetMapping("/getAll")
    public List<Comment> getAllComments(){
        return commentService.getAll();
    }
    @GetMapping("/getById/{id}")
    public Optional<Comment> getCommentById(@PathVariable Long id){
        return commentService.getById(id);
    }
    @PostMapping("/create")
    public Comment saveComment(@RequestBody Comment comment) throws Exception {
        Comment newComment = commentService.save(comment);
        return newComment;
    }
    @PutMapping("/update/{id}")
    public Comment updateComment(@PathVariable Long id,@RequestBody Comment comment) throws Exception {
        comment.setId(id);
        Comment updatedComment = commentService.save(comment);
        return updatedComment;
    }
    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.deleteById(id);
    }
}
