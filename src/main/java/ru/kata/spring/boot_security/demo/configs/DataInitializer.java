package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer  {
    @Bean
    public ApplicationRunner initData(UserRepository userRepository
            , RoleRepository roleRepository
            , PasswordEncoder passwordEncoder) {
        return args -> initializeData(userRepository, roleRepository, passwordEncoder);
    }

    private void initializeData(UserRepository userRepository
            , RoleRepository roleRepository
            , PasswordEncoder passwordEncoder) {

        Role userRole = roleRepository
                .findByName("ROLE_USER")
                .orElseGet(() -> roleRepository
                        .save(new Role("ROLE_USER")));
        Role adminRole = roleRepository
                .findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository
                        .save(new Role("ROLE_ADMIN")));

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(List.of(adminRole, userRole));
            admin.setName("Иван");
            admin.setLastName("Иванов");
            admin.setEmail("admin@email.com");
            admin.setAge(45);
            userRepository.save(admin);
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(List.of(userRole));
            user.setName("Александр");
            user.setLastName("Александров");
            user.setEmail("user@email.com");
            user.setAge(27);
            userRepository.save(user);
        }
    }
}
