package com.kechen.service;

import com.kechen.domain.Company;
import com.kechen.domain.Problem;
import com.kechen.domain.Problem_Company;
import com.kechen.repository.CompanyRepository;
import com.kechen.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // Get all the problems
    public List<Problem> getAllProblems(){
        return problemRepository.findAll();
    }

    // Get problems by tag
    public List<Problem> getProblemByTag(String tag){
        return  problemRepository.findAllByTag(tag);
    }

    // Get problems by difficulty
    public List<Problem> getProblemByDifficulty(int difficulty){
        return problemRepository.findAllByDifficulty(difficulty);
    }

    // Get problems by company name
    public List<Problem> getProblemByCompanyName(String companyName){
        Company company = companyRepository.findByCompanyName(companyName);
        List<Problem> list = new LinkedList<>();

        for(Problem_Company pc:company.getPcSet())
            list.add(pc.getProblem());

        return list;
    }

    public boolean Insert(Problem problem){
        return problemRepository.save(problem)!=null;
    }

    public void deleteProblem(Problem problem){
         problemRepository.delete(problem);
    }

    public void deleteProblemById(int id){
        problemRepository.delete(id);
    }
}
