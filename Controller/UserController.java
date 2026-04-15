package com.example.ecommerce.controller;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/add/user")
    public String addUser(User user){
        userService.createUser(user);
        return "LoginPage";
    }

    @PostMapping("/update/user")
    public String updateUser(User user){
        userService.updateUser(user);

        return "redirect:/admin/home";
    }

    @PostMapping("/delete/user")
    public String deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
        return "redirect:/admin/home";
    }
}
