package com.example.r2dbc_multi_tenant.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRequest {

    private String firstName;
    private String lastName;
}