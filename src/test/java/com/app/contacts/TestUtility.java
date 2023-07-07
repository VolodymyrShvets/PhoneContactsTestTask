package com.app.contacts;

import com.app.contacts.model.Contact;
import com.app.contacts.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtility {
    public static Contact Contact_1() {
        List<String> emails = new ArrayList<>();
        emails.add("vshkv@gmail.com");
        emails.add("vwvsdbnshkv@gmail.com");

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("+380690245321");

        Contact contact = new Contact();
        contact.setName("Liz");
        contact.setEmails(emails);
        contact.setPhoneNumbers(phoneNumbers);
        contact.setUser(null);

        return contact;
    }

    public static Contact Contact_2() {
        List<String> emails = new ArrayList<>();
        emails.add("vssdv78889hkv@gmail.com");

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("+380690245321");
        phoneNumbers.add("+380628355321");

        Contact contact = new Contact();
        contact.setName("Tom");
        contact.setEmails(emails);
        contact.setPhoneNumbers(phoneNumbers);
        contact.setUser(null);

        return contact;
    }

    public static User User_1() {
        User user = new User();
        user.setLogin("vs");
        user.setPassword("sh");
        user.setContacts(Arrays.asList(Contact_1(), Contact_2()));

        return user;
    }
}
