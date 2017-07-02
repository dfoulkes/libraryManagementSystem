package com.library.domain;

import com.library.enums.Role;

/**
 * Created by danfoulkes on 01/07/2017.
 * Project: libraryManagementSystem
 * Package: com.library.core
 */
public class User {

    private final Long id;
    private String username;
    private String password;
    private Role role;

    public User(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
