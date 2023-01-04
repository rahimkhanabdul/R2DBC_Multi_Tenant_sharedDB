package com.example.r2dbc_multi_tenant.repositories;

import com.example.r2dbc_multi_tenant.mapper.CustomerMapper;
import com.example.r2dbc_multi_tenant.model.CustomerDto;
import com.example.r2dbc_multi_tenant.request.CustomerRequest;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{

    private final DatabaseClient databaseClient;

    private final CustomerMapper customerMapper;

    public CustomerRepositoryImpl(DatabaseClient databaseClient, CustomerMapper customerMapper) {
        this.databaseClient = databaseClient;
        this.customerMapper = customerMapper;
    }

    public Mono<CustomerDto> save(String tenantID, CustomerRequest customerRequest) {
        String customerTable = tenantID+"_customer";
        String query = "INSERT INTO " + customerTable + "(first_name, last_name) VALUES (:firstName, :lastName)";

        return this.databaseClient.sql(query)
                .filter((statement, executeFunction) -> statement.returnGeneratedValues("id").execute())
                .bind("firstName", customerRequest.getFirstName())
                .bind("lastName", customerRequest.getLastName())
                .fetch()
                .first()
                .flatMap(result ->  findById(tenantID, (Long) result.get("id"))
                );
    }

    @Override
    public Mono<CustomerDto> findById(String tenantID, Long id) {
        String customerTable = tenantID+"_customer";
        String query = "SELECT * FROM " + customerTable + " where id=:id";
        return databaseClient.sql(query)
                .bind("id", id)
                .map(customerMapper::apply)
                .one();
    }

    @Override
    public Flux<CustomerDto> findAll(String tenantID) {
        String customerTable = tenantID+"_customer";
        String query = "SELECT * FROM " + customerTable;

        return databaseClient.sql(query)
                .map(customerMapper::apply)
                .all();
    }
}
