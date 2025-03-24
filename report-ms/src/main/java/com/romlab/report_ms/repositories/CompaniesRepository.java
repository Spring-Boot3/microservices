package com.romlab.report_ms.repositories;

import com.romlab.report_ms.beans.LoadBalancerConfiguration;
import com.romlab.report_ms.models.CompanyDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//Esta etiqueta es la fomra en la que los micros se comunican
//En el name debe ir el nombre cin el cual se tiene registrado el micro
//en el registry ejemplo companies
@FeignClient(name = "companies")
//Este es un valanceador de carga para el microservicio
@LoadBalancerClient(name = "companies", configuration = LoadBalancerConfiguration.class)
public interface CompaniesRepository {

    @GetMapping(path = "/api/companies/company/{name}")
    Optional<CompanyDTO> getByName(@PathVariable String name);

    @PostMapping(path = "/api/companies/company")
    Optional<CompanyDTO> postByName(@RequestBody CompanyDTO companyDTO);

    @DeleteMapping(path = "/api/companies/company/{name}")
    void deleteByName(@PathVariable String name);

}
