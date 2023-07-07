package com.app.contacts.service.validator.emailvalidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailListValidator.class)
public @interface UniqueEmailList {
    String message() default "Emails must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
