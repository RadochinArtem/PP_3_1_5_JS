package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String index(Model model, Principal principal) {
        model.addAttribute("current_user", userService.findUserByEmail(principal.getName()));
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("rolesList", roleService.findAllRoles());
        model.addAttribute("newUser", new User());
        return "admin/all";
    }
}