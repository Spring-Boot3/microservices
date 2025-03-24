package com.romlab.report_ms.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
public class LoadBalancerConfiguration {

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
        log.info("Configuring load balancer");
        return ServiceInstanceListSupplier.builder()
                .withBlockingDiscoveryClient()
//                .withSameInstancePreference() Lo retiramos porque vamos a trabajar con varias instancias no solo una
                .build(context);
    }

}
