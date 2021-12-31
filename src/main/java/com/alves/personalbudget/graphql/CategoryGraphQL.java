package com.alves.personalbudget.graphql;

import com.alves.personalbudget.graphql.input.CategoryInput;
import com.alves.personalbudget.model.Category;
import com.alves.personalbudget.service.CategoryService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CategoryService service;

    @Autowired
    ModelMapper modelMapper;

    public List<Category> findAllCategory(){
        return service.findAll();
    }

    public Category saveCategory(CategoryInput categoryInput){
        Category category = modelMapper.map(categoryInput, Category.class);
        return service.save(category);
    }

    public Category findCategoryById(Long id){
      return service.findById(id).get();
    }

    public Boolean deleteCategoryById(Long id){
        return service.deleteById(id);
    }

    public Category updateCategory(CategoryInput categoryInput){
        Category category = modelMapper.map(categoryInput, Category.class);
        return service.update(category);
    }
}
