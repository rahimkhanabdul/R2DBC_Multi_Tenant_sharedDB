package com.example.r2dbc_multi_tenant.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
}