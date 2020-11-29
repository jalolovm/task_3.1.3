package com.jalolov.task_312.controllers;

import com.jalolov.task_312.model.Role;
import com.jalolov.task_312.model.User;
import com.jalolov.task_312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminsControllers {

    private final UserService userService;

    @Autowired
    public AdminsControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model, Principal principal) {
        User userNow = userService.findByUsername(principal.getName());
        model.addAttribute("userNow", userNow);
        model.addAttribute("users", userService.getAll());
        model.addAttribute("user", new User());
        return "admin/user-list";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles_arr", required = false) String[] roles) throws Exception {

        List<String> rolesList = Arrays.asList(roles);
        if (rolesList.contains("ROLE_ADMIN")) {
            user.setRoles(Set.of(new Role(2L, "ROLE_ADMIN"),
                    new Role(1L, "ROLE_USER")));
        } else if ((rolesList.contains("ROLE_USER"))) {
            user.setRoles(Set.of(new Role(1L, "ROLE_USER")));
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("userUp") User user, @PathVariable("id") long id,
                         @RequestParam(value = "roles_arr", defaultValue = "ROLE_USER") String[] roles) {

        List<String> rolesList = Arrays.asList(roles);
        if (rolesList.contains("ROLE_ADMIN")) {
            user.setRoles(Set.of(new Role(2L, "ROLE_ADMIN"),
                    new Role(1L, "ROLE_USER")));
        } else if ((rolesList.contains("ROLE_USER"))) {
            user.setRoles(Set.of(new Role(1L, "ROLE_USER")));
        }
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}