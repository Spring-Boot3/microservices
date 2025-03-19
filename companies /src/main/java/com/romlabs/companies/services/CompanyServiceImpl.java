package com.romlabs.companies.services;

import com.romlabs.companies.entities.Company;
import com.romlabs.companies.enums.Category;
import com.romlabs.companies.repositories.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company create(Company company) {
        company.getWebSites().forEach(webSite -> {
            if(Objects.isNull(webSite.getCategory())) {
                webSite.setCategory(Category.NONE);
            }
        });
        return companyRepository.save(company);
    }

    @Override
    public Company readByName(String name) {
        return companyRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Company not found"));
    }

    @Override
    public Company upDate(Company company, String name) {
        var companyToUpdate = companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        companyToUpdate.setLogo(company.getLogo());
        companyToUpdate.setFoundationDate(company.getFoundationDate());
        companyToUpdate.setFounder(company.getFounder());
        return companyRepository.save(companyToUpdate);
    }

    @Override
    public void delete(String name) {
        var companyToDelete = companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        companyRepository.delete(companyToDelete);
    }

}
