package com.alves.personalbudget.graphql;

import com.alves.personalbudget.graphql.input.PersonInput;
import com.alves.personalbudget.graphql.response.PersonResponse;
import com.alves.personalbudget.model.Person;
import com.alves.personalbudget.service.PersonService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonGraphQL implements GraphQLQueryResolver {

    @Autowired
    private PersonService service;

    public Person findPersonById(Long id){
       Person person = service.findById(id).get();
        return person;
    }

   public PersonResponse findPeople(PersonInput filterPerson, int page, int size){
    Pageable pageable = PageRequest.of(page, size);
    Page<Person> objPage =  service.findByNameContaining(filterPerson.getName(), pageable);
    return new PersonResponse(objPage.getContent(), objPage.getTotalElements(), objPage.getTotalPages() );
   }

}
