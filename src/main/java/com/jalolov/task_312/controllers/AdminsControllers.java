package com.jalolov.task_312.controllers;

import com.jalolov.task_312.model.User;
import com.jalolov.task_312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminsControllers {

    private final UserService userService;

    @Autowired
    public AdminsControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String rest(Model model, Principal principal) {
        User userNow = userService.findByUsername(principal.getName());
        model.addAttribute("userNow", userNow);
        model.addAttribute("users", userService.getAll());
        model.addAttribute("user", new User());
             return "admin/rest-main";
    }
}