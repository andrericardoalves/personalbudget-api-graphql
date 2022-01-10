package com.alves.personalbudget.service;

import com.alves.personalbudget.model.Address;
import com.alves.personalbudget.model.Person;
import com.alves.personalbudget.repository.PersonRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PersonRepository repository;

    @Autowired
    private ContactService contactService;

    public Person update(Long id, Person person) {
        Person persistedPerson = findById(id).get();

        persistedPerson.setName(person.getName());
        persistedPerson.setAddress(person.getAddress());
        persistedPerson.setActive(person.getActive());
        persistedPerson.setContacts(person.getContacts());
        persistedPerson.getContacts().forEach(c -> c.setPerson(persistedPerson));


        return repository.save(persistedPerson);
    }

    public void updateActive(Long id, Boolean active) {
        Person persistedPerson = findById(id).get();
        persistedPerson.setActive(active);
        repository.save(persistedPerson);
    }

    @Transactional
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1)));
    }

    public Person save(Person person) {
        person.getContacts().forEach(c -> c.setPerson(person));
        person.setActive(true);
        return repository.save(person);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Page<Person> findByNameContaining(String name, Pageable pageable) {
        return repository.findByNameContaining(name, pageable);
    }

    public Address findAddressById(Long id){
        Person person = repository.findAddressById(id);
        return person.getAddress();
    }

    public List<Person> getPeople(Long cursor) {
        if (cursor == null) {
            return findAll();
        }
        return findPersonIdGreaterThan(cursor);
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public List<Person> findPersonIdGreaterThan(Long id) {
        return repository.findByIdGreaterThan(id);
    }
}
