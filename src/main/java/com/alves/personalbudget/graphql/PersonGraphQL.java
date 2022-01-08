package com.alves.personalbudget.graphql;

import com.alves.personalbudget.model.Person;
import com.alves.personalbudget.service.PersonService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonGraphQL implements GraphQLQueryResolver {

    @Autowired
    private PersonService service;

    public Person findPersonById(Long id){
       Person person = service.findById(id).get();
        return person;
    }

}
