package com.romlab.report_ms.beans;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerBean {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomCB() {
        var circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(60)
                .waitDurationInOpenState(Duration.ofSeconds(90))
                .slidingWindowSize(2)
                .build();

        var timeLimiterConfig = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(5))
                .build();

        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(circuitBreakerConfig)
                .timeLimiterConfig(timeLimiterConfig)
                .build());
    }

    // @Bean
    // public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
    //     return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
    //             .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
    //             .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
    //             .build());
    // }

    // @Bean
    // public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {
    //     return factory -> factory.configure(builder -> builder
    //             .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
    //             .circuitBreakerConfig(CircuitBreakerConfig.custom()
    //                     .slidingWindowSize(10)
    //                     .permittedNumberOfCallsInHalfOpenState(5)
    //                     .failureRateThreshold(50)
    //                     .waitDurationInOpenState(Duration.ofSeconds(10))
    //                     .build()));
    // }

    // @Bean
    // public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {
    //     return factory -> factory.configure(builder -> builder
    //             .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
    //             .circuitBreakerConfig(CircuitBreakerConfig.custom()
    //                     .slidingWindowSize(10)
    //                     .permittedNumberOfCallsInHalfOpenState(5)
    //                     .failureRateThreshold(50)
    //                     .waitDurationInOpenState(Duration.ofSeconds(10))
    //                     .build()));
    // }

    // @Bean
    // public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {
    //     return factory -> factory.configure(builder -> builder
    //             .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
    //             .circuitBreakerConfig(CircuitBreakerConfig.custom()
    //                     .slidingWindowSize(10)
    //                     .permittedNumberOfCallsInHalfOpenState(5)
    //                     .failureRateThreshold(50)
    //                     .waitDurationInOpenState(Duration.ofSeconds

}
