package com.nitinbhadoriya.journalingApp.controller;

import com.nitinbhadoriya.journalingApp.entity.User;
import com.nitinbhadoriya.journalingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping("/{userName}")
    public void updateUser(@RequestBody User user, @PathVariable String userName) {
        User existingUser = userService.findByUserName(userName);
        if(existingUser != null) {
            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            userService.createUser(existingUser);
        }
    }


}
