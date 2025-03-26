package com.romlab.companies_fallback.controllers;

import com.romlab.companies_fallback.models.CompanyDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;

@RestController
@RequestMapping("company")
@AllArgsConstructor
@Slf4j
public class CompanyController {

    private static final CompanyDTO DEFAULT_COMPANY = CompanyDTO.builder()
            .id(0L)
            .founder("Fallback")
            .name("Fallback Company")
            .logo("http://default-log.com")
            .foundationDate(LocalDate.now())
            .webSites(Collections.emptyList())
            .build();

    @GetMapping(path = "/{name}")
    public ResponseEntity<CompanyDTO> get(@PathVariable String name) {
        log.info("GET in fallback: company {}", name);
        return ResponseEntity.ok(DEFAULT_COMPANY);
    }

}
