package com.example.r2dbc_multi_tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
public class R2DbcMultiTenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(R2DbcMultiTenantApplication.class, args);
    }
}
