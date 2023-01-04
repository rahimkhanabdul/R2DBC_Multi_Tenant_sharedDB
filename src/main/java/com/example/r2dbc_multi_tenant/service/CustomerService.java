package com.example.r2dbc_multi_tenant.service;

import com.example.r2dbc_multi_tenant.model.CustomerDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CustomerService {
    Mono<CustomerDto> findById(Integer id);
    Mono<List<CustomerDto>> findAllByTenantId(String tenantID);
}
