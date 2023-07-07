package com.app.contacts.controller;

import com.app.contacts.model.dto.ContactRequest;
import com.app.contacts.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contacts")
@AllArgsConstructor
//@Validated
public class ContactController {
    private final ContactService contactService;
    private Validator validator;

    @PostMapping(value = "/new")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity addNewContact(@RequestBody ContactRequest contact) {
        Set<ConstraintViolation<ContactRequest>> violations = validator.validate(contact);
        if (!violations.isEmpty()) {
            List<String> errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        contactService.createNewContact(contact);

        return ResponseEntity.ok("New Contact successfully added");
    }

    @PutMapping(value = "/{oldName}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateContact(@PathVariable String oldName, @RequestBody ContactRequest newContactRequest) {
        Set<ConstraintViolation<ContactRequest>> violations = validator.validate(newContactRequest);
        if (!violations.isEmpty()) {
            List<String> errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        contactService.updateContact(oldName, newContactRequest);
        return ResponseEntity.ok("Contact successfully updated");
    }

    @GetMapping
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity getAllUserContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @DeleteMapping(value = "/{contactName}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteContact(@PathVariable String contactName) {
        contactService.deleteContact(contactName);
        return ResponseEntity.ok("Deleted");
    }
}
