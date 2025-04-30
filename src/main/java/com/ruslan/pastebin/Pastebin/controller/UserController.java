package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.UserDAO;
import com.ruslan.pastebin.Pastebin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    public UserDAO userDAO;

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userDAO.findById(id);
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        User savedUser = userDAO.save(user);
        return savedUser;
    }
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User updatedUser = userDAO.save(user);
        return updatedUser;
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDAO.deleteById(id);
    }
}
