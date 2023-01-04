package com.example.r2dbc_multi_tenant.service.impl;

import com.example.r2dbc_multi_tenant.constants.ApplicationConstants;
import com.example.r2dbc_multi_tenant.model.CustomerDto;
import com.example.r2dbc_multi_tenant.repositories.CustomerRepository;
import com.example.r2dbc_multi_tenant.service.CustomerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Mono<CustomerDto> findById(Integer id){
        return customerRepository.findById(id)
                .map(customer -> CustomerDto.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .tenantId(ApplicationConstants.TENANT_ID)
                        .build());
    }

    @Override
    public Mono<List<CustomerDto>> findAllByTenantId(String tenantID){
        System.out.println(tenantID);
        return customerRepository.findAllByTenantId(tenantID)
                .map(customer -> CustomerDto.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .tenantId(ApplicationConstants.TENANT_ID)
                        .build())
                .collectList();
    }
}
