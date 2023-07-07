package com.app.contacts.service;

import com.app.contacts.model.Contact;
import com.app.contacts.model.User;
import com.app.contacts.model.dto.ContactRequest;
import com.app.contacts.repository.ContactRepository;
import com.app.contacts.security.authentication.AuthenticationFacade;
import com.app.contacts.service.mapper.ContactMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ContactService {
    private final AuthenticationFacade authenticationFacade;
    private final ContactRepository contactRepository;
    private final UserService userService;

    public Contact createNewContact(ContactRequest contactRequest) {
        log.info("Adding new Contact with Name: {} for User: {}", contactRequest.getName(), authenticationFacade.getAuthentication().getPrincipal());

        Contact newContact = ContactMapper.INSTANCE.contactFormToContact(contactRequest);
        newContact.setUser(userService.getUserByLogin(authenticationFacade.getAuthentication().getPrincipal().toString()));

        return contactRepository.save(newContact);
    }

    public List<Contact> getAllContacts() {
        log.info("Receiving all Contacts for User: {}", authenticationFacade.getAuthentication().getPrincipal());

        User user = userService.getUserByLogin((String) authenticationFacade.getAuthentication().getPrincipal());

        return contactRepository.getAllByUser(user);
    }

    public Contact updateContact(String oldName, ContactRequest newContactRequest) {
        log.info("Updating Contact with Name: {}", oldName);

        Contact oldContact = contactRepository.findByName(oldName);
        if (oldContact == null) {
            log.warn("Contact with Name: {} not found", oldName);
            throw new IllegalArgumentException();
        }

        oldContact = ContactMapper.INSTANCE.populateContactWithPresentContactFormFields(oldContact, newContactRequest);
        Contact newContact = contactRepository.save(oldContact);
        log.info("Contact updated successfully");
        return newContact;
    }

    @Transactional
    public void deleteContact(String name) {
        log.info("Deleting Contact with Name: {}", name);
        contactRepository.deleteByName(name);
    }
}
