package com.app.contacts.service.mapper;

import com.app.contacts.model.Contact;
import com.app.contacts.model.dto.ContactRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact contactFormToContact(ContactRequest contactRequest);

    Contact populateContactWithPresentContactFormFields(@MappingTarget Contact newContact, ContactRequest oldContact);
}
