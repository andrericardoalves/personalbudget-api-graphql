package com.alves.personalbudget.graphql;

import com.alves.personalbudget.model.Contact;
import com.alves.personalbudget.model.Person;
import com.alves.personalbudget.service.ContactService;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonResolver implements GraphQLResolver<Person> {

    @Autowired
    private ContactService contactService;

    public List<Contact> contacts(Person person){
        return contactService.findContactByPersonId(person.getId());
    }
}
