package com.alves.personalbudget.handler;

import com.alves.personalbudget.exception.DomainException;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import graphql.validation.ValidationError;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BudgetGraphQLErrorHandler implements  GraphQLErrorHandler{
    @Autowired
    Environment env;

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        return list.stream().map(this::getErrors).collect(Collectors.toList());
    }

    private GraphQLError getErrors(GraphQLError error) {
        if (error instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) error;
            if (exceptionError.getException() instanceof DomainException) {
                Throwable ex = exceptionError.getException();
                String msg = ex.getMessage();
                return new SimpleError(msg);
            }

            String[] profiles = env.getActiveProfiles();
            boolean dev = ArrayUtils.contains(profiles,"dev");
            if(! dev ) {
              return new SimpleError("An error occurred processing the transaction.");
            }

        } else if (error instanceof ValidationError) {
            String msg = error.getMessage();
            return new SimpleError(msg);
        }
        return error;
    }
}