package com.alves.personalbudget.graphql;

import com.alves.personalbudget.model.State;
import com.alves.personalbudget.service.StateService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateGraphQL implements GraphQLQueryResolver {

    @Autowired
    private StateService service;

    public List<State> findAllStates(){
        return service.findAll();
    }
}
