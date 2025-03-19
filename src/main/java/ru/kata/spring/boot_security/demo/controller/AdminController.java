package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
//import jakarta.validation.Valid;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public String adminPanel(Model model, Principal principal) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("newUser", new User());
        model.addAttribute("authUser", userService.findByUsername(principal.getName()));
        //model.addAttribute("userEdit", userService.getUserById(id));
        //model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public String addUser(@ModelAttribute("newUser") @Valid User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/user/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}
