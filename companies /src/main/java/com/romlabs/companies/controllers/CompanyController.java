package com.romlabs.companies.controllers;

import com.romlabs.companies.entities.Company;
import com.romlabs.companies.services.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("company")
@AllArgsConstructor
@Slf4j
@Tag(name = "Companies resource")
public class CompanyController {

    private CompanyService companyService;

    @Operation(summary = "get a company given a company name")
    @GetMapping(path = "/{name}")
    public ResponseEntity<Company> get(@PathVariable String name) {
        log.info("GET: company {}", name);
        return ResponseEntity.ok(companyService.readByName(name));
    }

    @Operation(summary = "create in DB a company given a company from body")
    @PostMapping
    public ResponseEntity<Company> post(@RequestBody Company company) {
        log.info("POST: company {}", company.getName());
        return ResponseEntity.created(URI.create(companyService.create(company).getName())).build();
    }

    @Operation(summary = "update in DB a company given a company from body")
    @PutMapping(path = "/{name}")
    public ResponseEntity<Company> put(@PathVariable String name, @RequestBody Company company) {
        log.info("PUT: company {} {}", name, company);
        return ResponseEntity.ok(companyService.upDate(company, name));
    }

    @Operation(summary = "delete a company given a company name")
    @DeleteMapping(path = "/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        companyService.delete(name);
        log.info("DELETE: company {}", name);
        return ResponseEntity.noContent().build();
    }

}
