package com.library.builders;

import com.library.domain.User;
import com.library.enums.Role;

/**
 * Created by danfoulkes on 02/07/2017.
 * Project: libraryManagementSystem
 * Package: com.library.core
 */
public class UserBuilder {

    private Long id;
    private String username;
    private String password;
    private Role role;

    public  UserBuilder withUsername(String username){
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(String password){
        this.password = password;
        return this;
    }

    public UserBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public User build(){
        User user = new User(id);
        user.setPassword(password);
        user.setUsername(username);
        user.setRole(role);
        return user;
    }

    public static UserBuilder getBuilder(){
         return new UserBuilder();
    }


    public UserBuilder withRole(Role role) {
        this.role = role;
        return this;
    }
}
