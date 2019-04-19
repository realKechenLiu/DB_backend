package com.kechen.IdClasses;

import com.kechen.domain.Company;
import com.kechen.domain.Problem;

import java.io.Serializable;
import java.util.Objects;

public class PComId implements Serializable {

    private Problem problem;
    private Company company;

    public PComId() {
    }

    public PComId(Problem problem, Company company) {
        this.problem = problem;
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PComId)) return false;
        PComId pComId = (PComId) o;
        return Objects.equals(problem, pComId.problem) &&
                Objects.equals(company, pComId.company);
    }

    @Override
    public int hashCode() {

        return Objects.hash(problem, company);
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
