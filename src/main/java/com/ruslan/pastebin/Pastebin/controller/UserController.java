package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.CommentDAO;
import com.ruslan.pastebin.Pastebin.dao.UserDAO;
import com.ruslan.pastebin.Pastebin.entity.Comment;
import com.ruslan.pastebin.Pastebin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    public UserDAO userDAO;

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userDAO.getAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
        return userDAO.getById(id);
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        User savedUser = userDAO.save(user);
        return savedUser;
    }
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User updatedUser = userDAO.update(user);
        return updatedUser;
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userDAO.deleteById(id);
    }
}
