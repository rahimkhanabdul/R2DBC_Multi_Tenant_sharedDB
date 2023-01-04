package com.example.r2dbc_multi_tenant.controller;

import com.example.r2dbc_multi_tenant.model.CustomerDto;
import com.example.r2dbc_multi_tenant.request.CustomerRequest;
import com.example.r2dbc_multi_tenant.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.example.r2dbc_multi_tenant.constants.ApplicationConstants.TENANT_HEADER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/customers", produces = APPLICATION_JSON_VALUE)
    public Mono<CustomerDto> findById(@RequestHeader(name = TENANT_HEADER, defaultValue = "") String tenantId,
                                      @RequestBody CustomerRequest customerRequest) {
        return customerService.save(tenantId, customerRequest);
    }
    @GetMapping(value = "/customers/{id}", produces = APPLICATION_JSON_VALUE)
    public Mono<CustomerDto> findById(@RequestHeader(name = TENANT_HEADER, defaultValue = "") String tenantId,
                                      @PathVariable Long id) {
        return customerService.findById(tenantId, id);
    }

    @GetMapping(value = "/customers", produces = APPLICATION_JSON_VALUE)
    public Mono<List<CustomerDto>> findAll(@RequestHeader(name = TENANT_HEADER, defaultValue = "") String tenantId) {
        return customerService.findAll(tenantId);
    }
}
