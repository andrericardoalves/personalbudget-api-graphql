package com.alves.personalbudget.service;

import com.alves.personalbudget.exception.AttributeMandatoryException;
import com.alves.personalbudget.model.Category;
import com.alves.personalbudget.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1)));
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public Boolean deleteById(Long id) {
      if(findById(id).isPresent()){
        repository.deleteById(id);
        return  true;
      }
       return false;
    }

    public Category update(Category category) {
        Category categoryPersisted = null;

        if(category.getId() == null){
            throw new AttributeMandatoryException("Attribute ID is Mandatory !!!");
        }

        if (findById(category.getId()).isPresent()){
            categoryPersisted =  repository.save(category);
        }
        return  categoryPersisted;
    }

}
