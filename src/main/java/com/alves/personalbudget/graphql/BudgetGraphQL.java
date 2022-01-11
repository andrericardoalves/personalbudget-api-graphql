package com.alves.personalbudget.graphql;

import com.alves.personalbudget.dto.BudgetResumeDTO;
import com.alves.personalbudget.filter.BudgetFilter;
import com.alves.personalbudget.graphql.input.BudgetInput;
import com.alves.personalbudget.graphql.response.BudgetResponse;
import com.alves.personalbudget.graphql.response.BudgetResumeResponse;
import com.alves.personalbudget.model.Budget;
import com.alves.personalbudget.service.BudgetService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BudgetGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private BudgetService service;

    @Autowired
    ModelMapper mapper;

    public Budget saveBudget(BudgetInput budgetInput) {
        Budget budget = mapper.map(budgetInput, Budget.class);
        return service.save(budget);
    }

    public Budget updateBudget(Long id, BudgetInput budgetInput) {
        Budget budget = mapper.map(budgetInput, Budget.class);
        return service.update(id, budget);
    }

    public Budget findBudgetById(Long id) {
        return service.findById(id).get();
    }

    public boolean deleteBudgetById(Long id) {
        service.deleteById(id);
        return true;
    }

    public BudgetResponse findBudget(BudgetFilter budgetFilter, int page, int size ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Budget> objPage =  service.find(budgetFilter, pageable);
        return new BudgetResponse(objPage.getContent(), objPage.getTotalElements(), objPage.getTotalPages() );
    }

    public BudgetResumeResponse resumeBudget(BudgetFilter budgetFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BudgetResumeDTO> objPage = service.resume(budgetFilter, pageable);
        return new BudgetResumeResponse(objPage.getContent(), objPage.getTotalElements(), objPage.getTotalPages() );
    }
}
