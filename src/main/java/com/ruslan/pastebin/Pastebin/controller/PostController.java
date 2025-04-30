package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.PostDAO;
import com.ruslan.pastebin.Pastebin.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    public PostDAO postDAO;

    @GetMapping("/post")
    public List<Post> getAllPosts(){
        return postDAO.findAll();
    }

    @GetMapping("/post/{id}")
    public Optional<Post> getPostById(@PathVariable Long id){
        return postDAO.findById(id);
    }

    @PostMapping("/post")
    public Post savePost(@RequestBody Post post) {
        Post savedPost = postDAO.save(post);
        return savedPost;
    }
    @PutMapping("/post/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        Post updatedPost = postDAO.save(post);
        return updatedPost;
    }
    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable Long id) {
        postDAO.deleteById(id);
    }
}
