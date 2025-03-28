package com.dev.makov.rl_system.security;

import com.dev.makov.rl_system.dao.RoleRepository;
import com.dev.makov.rl_system.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    CommandLineRunner initRoles() {
        return args -> {
            createRoleIfNotFound("ROLE_ADMIN");
            createRoleIfNotFound("ROLE_DIRECTOR");
            createRoleIfNotFound("ROLE_TEACHER");
            createRoleIfNotFound("ROLE_STUDENT");
            createRoleIfNotFound("ROLE_PARENT");
        };
    }

    private void createRoleIfNotFound(String roleName) {
        if (roleRepository.findByName(roleName) == null) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}
