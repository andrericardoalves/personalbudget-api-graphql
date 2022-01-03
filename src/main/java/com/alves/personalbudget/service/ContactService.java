package com.alves.personalbudget.service;

import com.alves.personalbudget.model.Contact;
import com.alves.personalbudget.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public List<Contact> findContactByPersonId(Long id){
      return  repository.findContactByPersonId(id);
    }
}
