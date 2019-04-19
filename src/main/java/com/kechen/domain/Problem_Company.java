package com.kechen.domain;

import com.kechen.IdClasses.PComId;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "problem_company")
@IdClass(PComId.class)
public class Problem_Company implements Serializable {

    @Id
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Id
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Problem_Company() {
    }

    public Problem_Company(Problem problem, Company company) {
        this.problem = problem;
        this.company = company;
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
