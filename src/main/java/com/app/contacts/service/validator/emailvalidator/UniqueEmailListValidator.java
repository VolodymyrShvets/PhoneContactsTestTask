package com.app.contacts.service.validator.emailvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueEmailListValidator implements ConstraintValidator<UniqueEmailList, List<String>> {

    @Override
    public void initialize(UniqueEmailList constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<String> emails, ConstraintValidatorContext context) {
        if (emails == null) {
            return true; // Null list is considered valid
        }

        // Remove null or empty emails
        List<String> nonEmptyEmails = emails.stream()
                .filter(email -> email != null && !email.isEmpty())
                .collect(Collectors.toList());

        // Check for duplicates
        Set<String> uniqueEmails = new HashSet<>(nonEmptyEmails);
        if (uniqueEmails.size() != nonEmptyEmails.size()) {
            return false; // Duplicate emails found
        }

        // Check pattern for each email
        for (String email : nonEmptyEmails) {
            if (!email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b")) {
                return false; // Invalid email pattern
            }
        }

        return true;
    }
}
