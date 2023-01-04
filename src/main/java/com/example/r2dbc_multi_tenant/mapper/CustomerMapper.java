package com.example.r2dbc_multi_tenant.mapper;

import com.example.r2dbc_multi_tenant.model.CustomerDto;
import io.r2dbc.spi.Row;

import java.util.function.BiFunction;

public interface CustomerMapper extends BiFunction<Row, Object, CustomerDto> {

}
