package com.app.contacts.security.userdetails;

import com.app.contacts.model.User;
import com.app.contacts.model.exception.UserNotFoundException;
import com.app.contacts.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);

        if (user == null)
            throw new UserNotFoundException(login);

        log.info("Logging into {}", user.getLogin());

        return new CustomUserDetails.Builder()
                .withLogin(user.getLogin())
                .withPassword(user.getPassword())
                .withAuthorities(Collections.singletonList(new SimpleGrantedAuthority("User")))
                .build();
    }
}
