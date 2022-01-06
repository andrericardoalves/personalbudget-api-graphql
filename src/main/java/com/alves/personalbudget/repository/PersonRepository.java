package com.alves.personalbudget.repository;

import com.alves.personalbudget.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    public Page<Person> findByNameContaining(String name, Pageable pageable);

    @Query("select new Person(p.address) from Person p join fetch p.address a where p.id = :id")
    public Person findAddressById(Long id);
}
