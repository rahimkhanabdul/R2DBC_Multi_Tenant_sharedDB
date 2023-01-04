package com.example.r2dbc_multi_tenant.exception;

public class InvalidTenantException extends RuntimeException {

    public InvalidTenantException(String tenantId) {
        super(String.format("Invalid tenant Id %s", tenantId));
    }
}