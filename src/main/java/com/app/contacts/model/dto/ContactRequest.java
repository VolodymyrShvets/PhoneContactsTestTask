package com.app.contacts.model.dto;

import com.app.contacts.service.validator.emailvalidator.UniqueEmailList;
import com.app.contacts.service.validator.phonenumbervalidator.UniquePhoneNumberList;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class ContactRequest {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @UniqueEmailList
    private List<String> emails;
    @UniquePhoneNumberList
    private List<String> phoneNumbers;
}
