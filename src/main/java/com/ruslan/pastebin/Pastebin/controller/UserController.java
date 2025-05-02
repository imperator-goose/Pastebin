package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.UserDAO;
import com.ruslan.pastebin.Pastebin.entity.User;
import com.ruslan.pastebin.Pastebin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping("/user/all")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/user")
    public List<User> getAllActiveUsers(){
        return userService.getAllActive();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getById(id);
    }


    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return updatedUser;
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
