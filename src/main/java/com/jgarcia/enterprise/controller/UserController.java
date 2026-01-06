package com.jgarcia.enterprise.controller;

import com.jgarcia.enterprise.dto.CreateUserRequest;
import com.jgarcia.enterprise.dto.UpdateUserRolesRequest;
import com.jgarcia.enterprise.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public void create(@RequestBody CreateUserRequest user) {
        service.create(user);
    }

    @PutMapping("/{id}/roles")
    public void updateRoles(@PathVariable Long id, @RequestBody UpdateUserRolesRequest request){
        service.updateRoles(id, request);
    }
}
