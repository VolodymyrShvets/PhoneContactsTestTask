package com.app.contacts.service;

import com.app.contacts.model.Contact;
import com.app.contacts.repository.ContactRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.app.contacts.TestUtility.Contact_1;
import static com.app.contacts.TestUtility.Contact_2;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.Silent.class)
public class ContactServiceTest {
    @InjectMocks
    private ContactRepository contactRepository;

    @Test
    public void getAllContactsTest1() {
        List<Contact> contactList = new ArrayList<>(Arrays.asList(Contact_2()));

        Mockito.when(contactRepository.getAllByUser(Contact_2().getUser())).thenReturn((List<Contact>) Contact_2());

        List<Contact> actual = contactRepository.getAllByUser(Contact_2().getUser());

        Assert.assertEquals(actual.size(), contactList.size());
    }

    @Test
    public void getAllContactsTest2() {
        List<Contact> contactList = new ArrayList<>(Arrays.asList(Contact_1()));

        Mockito.when(contactRepository.getAllByUser(Contact_1().getUser())).thenReturn((List<Contact>) Contact_1());

        List<Contact> actual = contactRepository.getAllByUser(Contact_1().getUser());

        Assert.assertEquals(actual.size(), contactList.size());
    }
}
