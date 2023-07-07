package com.app.contacts.repository;

import com.app.contacts.model.Contact;
import com.app.contacts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    int deleteByName(String name);

    List<Contact> getAllByUser(User user);

    Contact findByName(String name);
}
