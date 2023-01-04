package com.example.r2dbc_multi_tenant.filter;

import com.example.r2dbc_multi_tenant.constants.ApplicationConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class TenantFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

        var xTenants = serverWebExchange.getRequest().getHeaders().get(ApplicationConstants.TENANT_HEADER);

        if (CollectionUtils.isEmpty(xTenants)) {
            return webFilterChain.filter(serverWebExchange);
        }

        //Will use the first tenant id from header
        return webFilterChain
                .filter(serverWebExchange)
                .contextWrite(context -> context.put(ApplicationConstants.TENANT_ID, xTenants.get(0)));
    }
}