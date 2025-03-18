package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RoleServiceImpl implements RoleService {
    private Set<Role> roles;
    private RoleRepository roleRepository;
    public RoleServiceImpl(Set<Role> roles, RoleRepository roleRepository) {
        this.roles = roles;
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    @Override
    public Role save(Role role){
        return roleRepository.save(role);
    }
    @Override
    public Optional<Role> findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public List<Role> findById(List<Long> ids){
        return roleRepository.findAllById(ids);

    }
}
