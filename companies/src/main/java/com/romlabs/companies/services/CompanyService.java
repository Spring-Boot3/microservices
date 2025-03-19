package com.romlabs.companies.services;

import com.romlabs.companies.entities.Company;

public interface CompanyService {

    Company create(Company company);
    Company readByName(String name);
    Company upDate(Company company, String name);
    void delete(String name);

}
