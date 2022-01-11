package com.alves.personalbudget.graphql.response;

import com.alves.personalbudget.dto.BudgetResumeDTO;
import com.alves.personalbudget.model.Budget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetResumeResponse {
    private List<BudgetResumeDTO> list;
    private long totalElements;
    private int totalPages;
}
