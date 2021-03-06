package com.alves.personalbudget.graphql.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInput {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private AddressInput address;
    private Boolean active;
    @NotEmpty
    private List<ContactInput> contacts;
}
