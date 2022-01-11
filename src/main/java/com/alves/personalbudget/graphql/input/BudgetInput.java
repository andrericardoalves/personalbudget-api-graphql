package com.alves.personalbudget.graphql.input;

import com.alves.personalbudget.model.BudgetKind;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetInput {

    private Long id;

    @NotNull
    private String description;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private LocalDate paymentDate;

    @NotNull
    private BigDecimal price;

    private String observation;

    @NotNull
    private BudgetKind kind;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long personId;
}
