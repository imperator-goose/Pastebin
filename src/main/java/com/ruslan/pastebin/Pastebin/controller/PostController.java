package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.PostDAO;
import com.ruslan.pastebin.Pastebin.entity.Post;
import com.ruslan.pastebin.Pastebin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    public PostService postService;

    @GetMapping("/post")
    public List<Post> getAllPosts(){
        return postService.getAll();
    }

    @GetMapping("/post/{id}")
    public Optional<Post> getPostById(@PathVariable Long id){
        return postService.getById(id);
    }

    @PostMapping("/post")
    public Post savePost(@RequestBody Post post) throws Exception {
        Post savedPost = postService.save(post);
        return savedPost;
    }
    @PutMapping("/post/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) throws Exception {
        post.setId(id);
        Post updatedPost = postService.save(post);
        return updatedPost;
    }
    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deleteById(id);
    }
}
