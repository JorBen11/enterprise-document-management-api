package com.jgarcia.enterprise.service;

import com.jgarcia.enterprise.dto.CreateUserRequest;
import com.jgarcia.enterprise.dto.UpdateUserRolesRequest;
import com.jgarcia.enterprise.entity.Role;
import com.jgarcia.enterprise.entity.User;
import com.jgarcia.enterprise.repository.RoleRepository;
import com.jgarcia.enterprise.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Role> roles = request.getRoles()
                .stream()
                .map(r -> roleRepository.findByName(r)
                        .orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        return userRepository.save(user);
    }

    public void updateRoles(Long userId, UpdateUserRolesRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Set<Role> roles = request.getRoles()
                .stream()
                .map(r -> roleRepository.findByName(r).orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toSet());

        user.setRoles(roles);
    }
}
