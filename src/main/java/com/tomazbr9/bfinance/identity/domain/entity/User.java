package com.tomazbr9.bfinance.identity.domain.entity;

import com.tomazbr9.bfinance.identity.domain.enums.UserRole;
import com.tomazbr9.bfinance.identity.domain.valueobject.Email;

import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private Email email;
    private String passwordHash;
    private UserRole role;

    public static User create(String name, Email email, String passwordHash){
        User user = new User();

        user.id = UUID.randomUUID();
        user.name = name;
        user.email = email;
        user.passwordHash = passwordHash;
        user.role = UserRole.USER;

        return user;

    }

    public static User restore(UUID id, String name, Email email, String passwordHash, UserRole role){
        User user = new User();

        user.id = id;
        user.name = name;
        user.email = email;
        user.passwordHash = passwordHash;
        user.role = role;

        return user;
    }

    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Email getEmail(){
        return email;
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    public UserRole getRole(){
        return role;
    }

}