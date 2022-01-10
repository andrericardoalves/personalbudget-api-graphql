package com.alves.personalbudget.graphql;

import com.alves.personalbudget.conection.CursorUtil;
import com.alves.personalbudget.graphql.input.PersonFilterInput;
import com.alves.personalbudget.graphql.input.PersonInput;
import com.alves.personalbudget.graphql.response.PersonResponse;
import com.alves.personalbudget.model.Person;
import com.alves.personalbudget.service.PersonService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import org.jetbrains.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private PersonService service;
    @Autowired
    private CursorUtil cursorUtil;
    @Autowired
    private ModelMapper modelMapper;

    public Person findPersonById(Long id){
       Person person = service.findById(id).get();
        return person;
    }

   public PersonResponse findPeople(PersonFilterInput filterPerson, int page, int size){
    Pageable pageable = PageRequest.of(page, size);
    Page<Person> objPage =  service.findByNameContaining(filterPerson.getName(), pageable);
    return new PersonResponse(objPage.getContent(), objPage.getTotalElements(), objPage.getTotalPages() );
   }

    public Connection<Person> findAllPeople(int first, @Nullable Long cursor) {
        List<Edge<Person>> edges = service.getPeople(cursor)
                .stream()
                .map(person -> new DefaultEdge<>(person,cursorUtil.createCursorWith(person.getId())))
                .limit(first)
                .collect(Collectors.toUnmodifiableList());

        var pageInfo = new DefaultPageInfo(
                cursorUtil.getFirstCursorFrom(edges),
                cursorUtil.getLastCursorFrom(edges),
                cursor != null,
                edges.size() >= first);

        return new DefaultConnection<>(edges, pageInfo);
    }

    public Person savePerson(PersonInput personInput){
        Person person = modelMapper.map(personInput, Person.class);
        return service.save(person);
    }

    public Person updatePerson(Long id, PersonInput personInput){
        Person person = modelMapper.map(personInput, Person.class);
        return service.update(id, person);
    }

    public boolean deletePersonById(Long id) {
        service.deleteById(id);
        return true;
    }
}
