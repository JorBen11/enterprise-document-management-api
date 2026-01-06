package com.jgarcia.enterprise.dto;

import java.util.Set;

public class UpdateUserRolesRequest {
    private Set<String> roles;

    public UpdateUserRolesRequest() {}

    public Set<String> getRoles() {
        return roles;
    }
    public void setRoles(Set<String> roles) {}
}
