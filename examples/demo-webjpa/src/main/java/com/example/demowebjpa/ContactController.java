package com.example.demowebjpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public List<Contact> getAll(@RequestParam(required = false) String lastName) {

        if (lastName == null) {
            return contactService.findAllContacts();
        }
        return contactService.findAllContactsByLastName(lastName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Integer id) {

        return contactService.findContactById(id)
            .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public void postContact(@RequestBody Contact payload) {

        contactService.saveContact(payload);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Integer id) {

        contactService.deleteContact(id);
    }
}

@Component
class ContactService {

    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAllContacts() {

        return contactRepository.findAll();
    }

    public void saveContact(Contact contact) {

        contactRepository.save(contact);
    }

    public void deleteContact(Integer id) {

        contactRepository.deleteById(id);
    }

    public Optional<Contact> findContactById(Integer id) {

        return contactRepository.findById(id);
    }

    public List<Contact> findAllContactsByLastName(String lastName) {

        return contactRepository.findAllByLastName(lastName);
//        return contactRepository.findAllByLastNameContains(lastName);
    }
}

interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findAllByLastNameContains(String lastName);
    List<Contact> findAllByLastName(String lastName);
}

@Entity
class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName, lastName, email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
