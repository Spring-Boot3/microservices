package com.romlab.report_ms.controllers;

import com.romlab.report_ms.services.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "report")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/{name}")
    public ResponseEntity<Map<String, String>> get(@PathVariable String name) {
        log.info("GET name {}", name );
        var response = Map.of("report", reportService.makeReport(name));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody String report) {
        log.info("POST report {}", report );
        return ResponseEntity.ok(reportService.saveReport(report));
    }

    @DeleteMapping(path = "/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        log.info("DELETE: company {}", name);
        reportService.deleteReport(name);
        return ResponseEntity.noContent().build();
    }

}
