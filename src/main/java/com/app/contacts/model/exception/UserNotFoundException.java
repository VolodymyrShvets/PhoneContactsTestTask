package com.app.contacts.model.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String login) {
        super("User with Login: " + login + " not found.");
    }
}
