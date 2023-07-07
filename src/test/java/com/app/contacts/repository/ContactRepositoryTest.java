package com.app.contacts.repository;

import com.app.contacts.model.Contact;
import com.app.contacts.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.app.contacts.TestUtility.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository repository;

    @Before
    public void setUp() throws Exception {
        List<Contact> contactList = new ArrayList<>(Arrays.asList(Contact_1(), Contact_2()));
        List<Contact> saved = repository.saveAll(contactList);
    }

    @Test
    public void findByNameTest() {
        Assert.assertEquals(Contact_1().toString(), repository.findByName(Contact_1().getName()).toString());
        Assert.assertEquals(Contact_2().toString(), repository.findByName(Contact_2().getName()).toString());
    }

    @Test
    public void deleteByNameTest() {
        int deletedCount = repository.deleteByName(Contact_1().getName());

        Assert.assertEquals(1, deletedCount);
    }
}
