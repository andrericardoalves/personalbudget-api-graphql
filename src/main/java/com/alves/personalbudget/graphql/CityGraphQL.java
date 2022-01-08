package com.alves.personalbudget.graphql;

import com.alves.personalbudget.model.City;
import com.alves.personalbudget.service.CityService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityGraphQL implements GraphQLQueryResolver {

    @Autowired
    private CityService service;

    public List<City> findCityByStateId(Long id){
        return service.findCityByStateId(id);
    }
}
