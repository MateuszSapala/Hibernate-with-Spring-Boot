package com.sapalamateusz.HibernatewithSpringBoot.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("Could not found user "+id);
    }
}
