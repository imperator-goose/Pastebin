package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.PostDAO;
import com.ruslan.pastebin.Pastebin.entity.Post;
import com.ruslan.pastebin.Pastebin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    public PostDAO postDAO;

    @GetMapping("/post")
    public List<Post> getAllPosts(){
        return postDAO.getAll();
    }

    @GetMapping("/post/{id}")
    public Post getPostById(@PathVariable int id){
        return postDAO.getById(id);
    }

    @PostMapping("/post")
    public Post savePost(@RequestBody Post post) {
        Post savedPost = postDAO.save(post);
        return savedPost;
    }
    @PutMapping("/post/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        Post updatedPost = postDAO.update(post);
        return updatedPost;
    }
    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable int id) {
        postDAO.deleteById(id);
    }
}
