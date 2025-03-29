package com.romlab.reportListener.streams;

import com.romlab.reportListener.documents.ReportDocument;
import com.romlab.reportListener.repositories.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Consumer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ReportListener {

    private final ReportRepository reportRepository;

    @Bean
    public Consumer<String> consumerReport() {
        return report -> {
            reportRepository.save(
                    ReportDocument.builder()
                    .id(UUID.randomUUID().toString())
                    .content(report).build()
            );
            log.info("Report saved to database: {}", report);
        };
    };

}
