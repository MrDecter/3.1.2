package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService usersService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService usersService, RoleService roleService) {
        this.usersService = usersService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Model model){
        model.addAttribute("users", usersService.getAllUsers());
        return "admin/show_all_users";
    }
    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAll());
        return "admin/new";
    }
    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", usersService.getUser(id));
        return "admin/show_user";
    }
    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {return "admin/new";}
        usersService.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/user/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", usersService.getUser(id));
        model.addAttribute("roles", roleService.getAll());
        return "admin/edit";
    }
    @PostMapping("/user/{id}")
    public String update(@ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()) {return "admin/edit";}
        usersService.update(user);
        return "redirect:/admin";
    }
    @GetMapping("/user/{id}/delete")
    public String delete(@PathVariable("id") int id){
        usersService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAll();
    }

}
