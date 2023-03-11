package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    private final UserService userService;
    private final RoleService roleService;

    public RestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public String index(Principal principal) {
        ModelAndView model = new ModelAndView("admin/all");
        model.addObject("current_user", userService.findUserByEmail(principal.getName()));
        model.addObject("users", userService.findAllUsers());
        model.addObject("rolesList", roleService.findAllRoles());
        model.addObject("newUser", new User());
        return "admin/all";
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUserById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(userService.findUserById(id),HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(name = "id") Long id) {
        userService.updateUser(user,id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Long> deleteUser (@PathVariable(name = "id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @GetMapping("/users/current_user")
    public ResponseEntity<User> showCurrentUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getListRoles(){
        return new ResponseEntity<>(roleService.findAllRoles(),HttpStatus.OK);
    }



}
