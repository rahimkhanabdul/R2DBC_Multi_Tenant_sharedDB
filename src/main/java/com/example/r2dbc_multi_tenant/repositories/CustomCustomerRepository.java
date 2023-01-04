package com.example.r2dbc_multi_tenant.repositories;

import com.example.r2dbc_multi_tenant.entity.Customer;
import com.example.r2dbc_multi_tenant.model.CustomerDto;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Flux;

public interface CustomCustomerRepository {
    Flux<CustomerDto> findAllByTenantId(String tenantID);
}
