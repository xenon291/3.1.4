package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    void save(User user);

    public void update(Long id, User user);

    void delete(Long id);

    User findByUsername(String username);
}
