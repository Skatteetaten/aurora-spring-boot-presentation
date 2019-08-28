package com.example.demowebjpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ContactServiceTest {

    @Autowired
    ContactService contactService;

    @MockBean
    ContactRepository contactRepository;

    @Test
    public void returnsEmptyOptionalForNonexistingContacts() {

        Optional<Contact> contact = contactService.findContactById(1);

        assertThat(contact).isEmpty();
    }

    @Test
    public void returnsContactForExistingContacts() {

        given(contactRepository.findById(1)).willReturn(Optional.of(new Contact()));
        Optional<Contact> contact = contactService.findContactById(1);

        assertThat(contact).isNotEmpty();
    }
}
