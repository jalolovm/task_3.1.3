package com.jalolov.task_312.controllers;

import com.jalolov.task_312.model.User;
import com.jalolov.task_312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersControllers {

    private final UserService userService;

    @Autowired
    public UsersControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String show(Principal principal, Model model) {
        User userNow = userService.findByUsername(principal.getName());
        model.addAttribute("userNow", userNow);
        return "users/user";
    }
}
