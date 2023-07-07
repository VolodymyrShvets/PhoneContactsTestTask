package com.app.contacts.service.validator.phonenumbervalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumberList, List<String>> {
    @Override
    public void initialize(UniquePhoneNumberList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> numbers, ConstraintValidatorContext constraintValidatorContext) {
        if (numbers == null) {
            return true; // Null list is considered valid
        }

        // Remove null or empty numbers
        List<String> nonEmptyNumbers = numbers.stream()
                .filter(number -> number != null && !numbers.isEmpty())
                .collect(Collectors.toList());

        // Check for duplicates
        Set<String> uniquePhoneNumbers = new HashSet<>(nonEmptyNumbers);
        if (uniquePhoneNumbers.size() != nonEmptyNumbers.size()) {
            return false; // Duplicate numbers found
        }

        // Check pattern for each phone number
        for (String number : nonEmptyNumbers) {
            if (!number.matches("^\\+380\\d{9}$")) {
                return false; // Invalid phone number pattern
            }
        }

        return true;
    }
}
