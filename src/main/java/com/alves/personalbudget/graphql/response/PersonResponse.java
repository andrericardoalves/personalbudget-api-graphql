package com.alves.personalbudget.graphql.response;

import com.alves.personalbudget.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {
    private List<Person> list;
    private long totalElements;
    private int totalPages;
}
