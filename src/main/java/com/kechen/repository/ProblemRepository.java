package com.kechen.repository;

import com.kechen.domain.Problem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends CrudRepository<Problem,Integer>{

    public Problem findByProblemId(int id);

    // Get all the problems
    public List<Problem> findAll();

    // Get problem by title
    public Problem findByTitle(String title);

    // Get problems by difficulty
    public List<Problem> findAllByDifficulty(int difficulty);

    // Get problems by tag
    public List<Problem> findAllByTag(String tag);

}
