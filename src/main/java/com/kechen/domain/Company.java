package com.kechen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private int companyId;

    @Column(name = "company_name")
    private String companyName;

    @JsonIgnore
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<Problem_Company> pcSet;

    public Company() {
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<Problem_Company> getPcSet() {
        return pcSet;
    }

    public void setPcSet(Set<Problem_Company> pcSet) {
        this.pcSet = pcSet;
    }
}
