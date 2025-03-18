package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    public Role save(Role role);
    public Optional<Role> findByName(String role);
    public List<Role> findAll();
    public List<Role> findById(List<Long> ids);

}
