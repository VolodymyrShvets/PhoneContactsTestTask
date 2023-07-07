package com.app.contacts.config;

import com.app.contacts.security.authentication.MyAuthenticationProvider;
import com.app.contacts.security.userdetails.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final MyAuthenticationProvider authenticationProvider;
    private final MyUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .userDetailsService(userDetailsService)
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/register").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .rememberMe(Customizer.withDefaults());

//        http
//                .userDetailsService(userDetailsService)
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/register", "/login")
//                .anonymous()
////                .permitAll()
////                .anyRequest()
//                .requestMatchers("/logout")
//                .authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .defaultSuccessUrl("/", true)
////                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .invalidateHttpSession(true)
//                .permitAll();
        http
                .userDetailsService(userDetailsService)
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/register**", "/login**")
                .permitAll()
                .anyRequest()
                .authenticated()
//                .requestMatchers("/info", "/logout")
//                .permitAll().anyRequest().authenticated()
                //.anyRequest().denyAll()
                .and()
                .formLogin()
                .loginPage("/login")
                //.disable()
                .and()
                .logout()
                .permitAll();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> {
            try {
                return authenticationProvider.authenticate(authentication);
            } catch (AuthenticationException e) {
                throw new AuthenticationServiceException("Authentication failed", e);
            }
        };
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }
}
