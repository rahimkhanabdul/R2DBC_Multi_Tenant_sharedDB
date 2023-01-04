package com.example.r2dbc_multi_tenant.repositories;

import com.example.r2dbc_multi_tenant.constants.ApplicationConstants;
import com.example.r2dbc_multi_tenant.model.CustomerDto;
import io.r2dbc.spi.Row;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.BiFunction;

public class CustomCustomerRepositoryImpl implements CustomCustomerRepository{

    private final DatabaseClient client;
    private final Converter converter;

    public CustomCustomerRepositoryImpl(DatabaseClient client, Converter converter) {
        this.client = client;
        this.converter = converter;
    }

    @Override
    public Flux<CustomerDto> findAllByTenantId(String tenantID) {
        String query = String.format(" SELECT * FROM s%_customer" +tenantID);

        return client.sql(query)
                .map(converter::apply)
                .all();
    }

    @Component
    static
    class Converter implements BiFunction<Row, Object, CustomerDto> {

        @Override
        public CustomerDto apply(Row row, Object customerDto) {
            return CustomerDto.builder()
                    .id(row.get("id", Integer.class))
                    .firstName(row.get("first_name", String.class))
                    .lastName(row.get("last_name", String.class))
                    .tenantId(ApplicationConstants.TENANT_ID)
                    .build();
        }
    }
}
