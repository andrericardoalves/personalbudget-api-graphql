package com.alves.personalbudget.graphql;

import com.alves.personalbudget.model.Category;
import com.alves.personalbudget.service.CategoryService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryGraphQL implements GraphQLQueryResolver {

    @Autowired
    private CategoryService service;

    public List<Category> categoryFindAll(){
        return service.findAll();
    }
}
