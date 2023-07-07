package com.app.contacts.repository;

import com.app.contacts.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.app.contacts.TestUtility.User_1;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.save(User_1());
    }

    @Test
    public void findByLoginTest() {
        User actual = repository.findByLogin(User_1().getLogin());

        Assert.assertEquals(User_1(), actual);
    }
}
