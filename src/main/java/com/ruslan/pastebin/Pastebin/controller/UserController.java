package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.UserDAO;
import com.ruslan.pastebin.Pastebin.entity.User;
import com.ruslan.pastebin.Pastebin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/getAllActive")
    public List<User> getAllActiveUsers(){
        return userService.getAllActive();
    }

    @GetMapping("/getById/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getById(id);
    }


    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return updatedUser;
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
