package com.romlab.report_ms.streams;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportPublisher {

    private final StreamBridge streamBridge;

    /*
     * topic name -> consumerReport
    */
    public void publishReport(String report) {
        streamBridge.send("consumerReport", report);
        streamBridge.send("consumerReport-in-0", report);
        streamBridge.send("consumerReport-out-0", report);
    }

}
