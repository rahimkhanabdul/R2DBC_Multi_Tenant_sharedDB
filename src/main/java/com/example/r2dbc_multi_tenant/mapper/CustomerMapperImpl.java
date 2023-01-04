package com.example.r2dbc_multi_tenant.mapper;

import com.example.r2dbc_multi_tenant.constants.ApplicationConstants;
import com.example.r2dbc_multi_tenant.model.CustomerDto;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto apply(Row row, Object customer) {
        return CustomerDto.builder()
                .id(row.get("id", Long.class))
                .firstName(row.get("first_name", String.class))
                .lastName(row.get("last_name", String.class))
//                .tenantId(ApplicationConstants.TENANT_ID)
                .build();
    }
}
