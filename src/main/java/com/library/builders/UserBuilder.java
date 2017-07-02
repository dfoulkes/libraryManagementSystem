package com.library.builders;

import com.library.domain.User;

/**
 * Created by danfoulkes on 02/07/2017.
 * Project: libraryManagementSystem
 * Package: com.library.core
 */
public class UserBuilder {

    private Long id;
    private String username;
    private String password;

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
        return user;
    }

    public static UserBuilder getBuilder(){
         return new UserBuilder();
    }



}
