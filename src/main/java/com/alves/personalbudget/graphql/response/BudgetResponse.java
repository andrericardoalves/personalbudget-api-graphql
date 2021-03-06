package com.alves.personalbudget.graphql.response;

import com.alves.personalbudget.model.Budget;
import com.alves.personalbudget.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetResponse {
    private List<Budget> list;
    private long totalElements;
    private int totalPages;
}
