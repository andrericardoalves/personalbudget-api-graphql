package com.alves.personalbudget.graphql;

import com.alves.personalbudget.model.Address;
import com.alves.personalbudget.model.Contact;
import com.alves.personalbudget.model.Person;
import com.alves.personalbudget.service.ContactService;
import com.alves.personalbudget.service.PersonService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonResolver implements GraphQLResolver<Person> {

    @Autowired
    private PersonService personService;

    @Autowired
    private ContactService contactService;

    public Address address(Person person){
        return personService.findAddressById(person.getId());
    }
    public List<Contact> contacts(Person person){
        return contactService.findContactByPersonId(person.getId());
    }
}
