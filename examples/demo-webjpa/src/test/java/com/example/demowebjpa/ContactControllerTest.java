package com.example.demowebjpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ContactService contactService;

    @Test
    public void contactResponseContainsExpectedFields() throws Exception {

        Contact contact = new Contact() {{
            setId(1);
            setFirstName("Bent André");
            setLastName("Solheim");
            setEmail("BentAndre.Solheim@Skatteetaten.no");
        }};
        given(contactService.findContactById(1))
            .willReturn(Optional.of(contact));

        String contentAsString = mvc.perform(get("/contact/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(contact.getId())))
            .andExpect(jsonPath("$.firstName", is(contact.getFirstName())))
            .andExpect(jsonPath("$.lastName", is(contact.getLastName())))
            .andExpect(jsonPath("$.email", is(contact.getEmail())))
            .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
}
