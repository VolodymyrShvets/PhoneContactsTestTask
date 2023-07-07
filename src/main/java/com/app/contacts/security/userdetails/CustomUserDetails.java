package com.app.contacts.security.userdetails;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {
    private final String login;
    private final String password;

    private CustomUserDetails(Builder builder) {
        super(builder.email, builder.password, builder.authorities);
        this.login = builder.email;
        this.password = builder.password;
    }

    public static class Builder {
        private String email;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public Builder withLogin(String login) {
            this.email = login;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withAuthorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public CustomUserDetails build() {
            return new CustomUserDetails(this);
        }
    }
}
