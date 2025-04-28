package com.ruslan.pastebin.Pastebin.controller;

import com.ruslan.pastebin.Pastebin.dao.CommentDAO;
import com.ruslan.pastebin.Pastebin.dao.UserDAO;
import com.ruslan.pastebin.Pastebin.entity.Comment;
import com.ruslan.pastebin.Pastebin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveUser(@RequestBody  User user){
        userDAO.save(user);
    }
    @PutMapping("/user")
    public void updateUser(@RequestBody User user){
        userDAO.update(user);
    }
    @DeleteMapping("/user/id")
    public void deleteUser(int id){
        userDAO.deleteById(id);
    }
}
