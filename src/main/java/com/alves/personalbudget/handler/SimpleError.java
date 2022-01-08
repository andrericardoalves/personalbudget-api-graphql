package com.alves.personalbudget.handler;


import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.kickstart.execution.error.GenericGraphQLError;


import java.util.List;
import java.util.Map;

class SimpleError extends GenericGraphQLError {

    SimpleError(String message) {
        super(message);
    }

    @Override
    @JsonIgnore
    public List<Object> getPath() {
        return null;
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getExtensions() {
        return null;
    }
}

