package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class ApiController {

    private final RoleService roleService;
    private final UserService userService;

    public ApiController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public List<User> indexView() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public ResponseEntity<HttpStatus> addUserView(@RequestBody @Validated User user, BindingResult bindingResult) {
        userService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/addOrUpdate/{id}")
    public ResponseEntity<HttpStatus> add(@PathVariable("id") int id, @RequestBody @Validated User user, BindingResult bindingResult) {
        String pas = getUserById(id).getPassword();
        user.setId(id);
        userService.update(user);
        if(user.getPassword().isEmpty()) {
            user.setPassword(pas);
            user.setId(id);
            userService.update(user);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        else {
            userService.save(user);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/removeUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAll();
        return ResponseEntity.ok().body(roles);
    }

}