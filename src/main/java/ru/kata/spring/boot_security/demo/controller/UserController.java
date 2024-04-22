package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.security.sasl.AuthenticationException;

@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("{id}")
	String getUser (@PathVariable("id") int id, Model model) throws AuthenticationException {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getRoles().stream().allMatch(r-> r.getRolename().equals("ROLE_USER")) && !(currentUser.getId() == id)){
			throw new AuthenticationException("Ошибка аутентификации");
		}
		model.addAttribute("user", userService.getUser(id));
		return "/users/show_user";
	}
}