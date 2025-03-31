package com.romlabs.companies.configs;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(999)
@Component
public class LogObservationHandler implements ObservationHandler<Observation.Context> {

    @Override
    public void onStart(Observation.Context context) {
        log.info("LogObservationHandler::onStart: {}", context.getName());
    }

    @Override
    public void onError(Observation.Context context) {
        log.info("LogObservationHandler::OnError: {}", context.getName());
    }

    @Override
    public void onStop(Observation.Context context) {
        log.info("LogObservationHandler::OnStop: {}", context.getName());
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }

}
