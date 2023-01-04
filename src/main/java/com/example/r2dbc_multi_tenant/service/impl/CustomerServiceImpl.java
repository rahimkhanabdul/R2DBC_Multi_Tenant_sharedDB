package com.example.r2dbc_multi_tenant.service.impl;

import com.example.r2dbc_multi_tenant.model.CustomerDto;
import com.example.r2dbc_multi_tenant.repositories.CustomerRepository;
import com.example.r2dbc_multi_tenant.request.CustomerRequest;
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
    public Mono<CustomerDto> save(String tenantID, CustomerRequest customerRequest) {
        return customerRepository.save(tenantID, customerRequest);
    }

    @Override
    public Mono<CustomerDto> findById(String tenantID, Long id){
        return customerRepository.findById(tenantID, id)
                .map(customer -> CustomerDto.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .build());
    }

    @Override
    public Mono<List<CustomerDto>> findAll(String tenantID){
        return customerRepository.findAll(tenantID)
                .map(customer -> CustomerDto.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .build())
                .collectList();
    }
}
