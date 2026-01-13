package com.SpringSecurityApp.SpringSecurityApp.dto;

import com.SpringSecurityApp.SpringSecurityApp.entities.enums.Permission;
import com.SpringSecurityApp.SpringSecurityApp.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {
    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
    private Set<Permission> permissions;
}
