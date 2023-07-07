package com.app.contacts.controller;

import com.app.contacts.model.User;
import com.app.contacts.model.dto.LoginForm;
import com.app.contacts.model.dto.RegistrationRequest;
import com.app.contacts.security.authentication.AuthenticationFacade;
import com.app.contacts.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationFacade authenticationFacade;

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        User user = userService.registerNewUser(registrationRequest);

        userService.performLogin(user.getLogin(), user.getPassword());

        return ResponseEntity.ok("Registration successful. User logged in");
    }

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        userService.performLogin(loginForm.getLogin(), loginForm.getPassword());
        return ResponseEntity.ok("Login successful. Welcome!");
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<String> logout() {
        System.out.println("logout");
        return ResponseEntity.ok("Logout successful. Goodbye!");
    }

    @GetMapping(value = "/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("Some info");
    }
}
