package com.app.contacts.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String login;
    private String password;
}
