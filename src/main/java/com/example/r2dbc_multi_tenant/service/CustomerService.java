package com.example.r2dbc_multi_tenant.service;

import com.example.r2dbc_multi_tenant.model.CustomerDto;
import com.example.r2dbc_multi_tenant.request.CustomerRequest;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CustomerService {
    Mono<CustomerDto> save(String tenantID, CustomerRequest customerRequest);

    Mono<CustomerDto> findById(String tenantID, Long id);

    Mono<List<CustomerDto>> findAll(String tenantID);
}
