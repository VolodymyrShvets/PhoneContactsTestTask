package com.app.contacts.service;

import com.app.contacts.model.User;
import com.app.contacts.model.dto.RegistrationRequest;
import com.app.contacts.repository.UserRepository;
import com.app.contacts.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public User registerNewUser(RegistrationRequest registrationRequest) {
        log.info("Registering new User with Login: {}", registrationRequest.getLogin());

        User newUser;
        if (userRepository.existsByLogin(registrationRequest.getLogin())) {
            log.warn("User with Login {} already exists. Performing login", registrationRequest.getLogin());

            newUser = userRepository.findByLogin(registrationRequest.getLogin());
        } else {
            newUser = userRepository.save(UserMapper.INSTANCE.registrationRequestToUser(registrationRequest));

            log.info("User with Login {} successfully created", newUser.getLogin());
        }

        return newUser;
    }

    public void performLogin(String login, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login,
                password
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("User with Login {} successfully logined", login);
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
