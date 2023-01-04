package com.example.r2dbc_multi_tenant.repositories;

import com.example.r2dbc_multi_tenant.model.CustomerDto;
import com.example.r2dbc_multi_tenant.request.CustomerRequest;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CustomerRepository {

    Mono<CustomerDto> save(String tenantID, CustomerRequest customerRequest);

    Mono<CustomerDto> findById(String tenantID, Long id);

    Flux<CustomerDto> findAll(String tenantID);
}
