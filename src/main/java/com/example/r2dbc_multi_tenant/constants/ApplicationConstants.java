package com.example.r2dbc_multi_tenant.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConstants {
    public static final String TENANT_HEADER = "X-Tenant";
    public static final String TENANT_ID = "TenantID";
}
