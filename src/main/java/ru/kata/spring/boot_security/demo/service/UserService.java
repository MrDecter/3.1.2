package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    boolean addUser(User user);
    void updateUser(User user);
    boolean deleteUser(int id);
    User getUser(int id);
    List<User> getUsers();
}
