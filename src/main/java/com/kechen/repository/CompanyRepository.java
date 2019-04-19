package com.kechen.repository;

import com.kechen.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company,Integer>{

    // Find all companies
    public List<Company> findAll();

    // Find by company_name
    public Company findByCompanyName(String companyName);

}
