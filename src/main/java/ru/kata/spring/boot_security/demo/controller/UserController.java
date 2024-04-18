package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping( "/index")
	public String allUsers(Model model) {
		List<User> usersList = userService.getUsers();
		model.addAttribute("users", usersList);
		return "index";
	}

	@GetMapping( "/user")
	public String roleUser(Model model) {
		List<User> usersList = userService.getUsers();
		model.addAttribute("users", usersList);
		return "user";
	}


	@GetMapping("/add")
	public String createUserForm(@ModelAttribute("user") User user) {
		return "add";
	}

	@PostMapping("/addUser")
	public String addUser(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return "redirect:/";
	}

	@GetMapping("/deleteUser")
	public String removeUser(@RequestParam("id") int id) {
		userService.deleteUser(id);
		return "redirect:/";
	}

	@GetMapping("/edit")
	public String getEditUserForm(Model model, @RequestParam("id") int id) {
		model.addAttribute("user", userService.getUser(id));
		return "edit";
	}

	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user) {
		userService.updateUser(user);
		return "redirect:/";
	}
}