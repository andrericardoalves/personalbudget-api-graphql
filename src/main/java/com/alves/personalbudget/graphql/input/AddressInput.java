package com.alves.personalbudget.graphql.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInput {
    @NotEmpty
    private String address;
    @NotEmpty
    private String addressNumber;
    private String complement;
    private String district;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private Long cityId;
}
