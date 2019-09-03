package com.example.demowebjpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class ContactRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    @Test
    public void findAllByLastName() {

        String lastName = "Solheim";
        List<Contact> contacts = contactRepository.findAllByLastName(lastName);

        assertThat(contacts.size()).isEqualTo(2);
        assertThat(contacts).allMatch(it -> it.getLastName().equals(lastName));
    }
}