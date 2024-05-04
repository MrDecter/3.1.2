package ru.kata.spring.boot_security.demo.controller;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final UserRepository userRepository;

	public UserController(UserService userService, UserRepository userRepository) {
		this.userService = userService;
        this.userRepository = userRepository;
    }

	@GetMapping()
	public String showAllUsers(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		System.out.println("Current username: " + currentUsername);
		User user = userRepository.findByUsername(currentUsername);
		model.addAttribute("user", user);
		return "users/show_user";
	}
}