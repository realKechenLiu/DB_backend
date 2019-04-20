package com.kechen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "problem")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "problem_id")
    private int problemId;


    private String description;
    private String title;
    private int difficulty;
    private String tag;


    public int getId(){
        return problemId;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "problem",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<User_Problem> upSet;

    @JsonIgnore
    @OneToMany(mappedBy = "problem",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<Problem_Company> pcomSet;

    @JsonIgnore
    @OneToMany(mappedBy = "problem",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<Problem_Code> pcodeSet;

    public Problem() {
    }

    public Problem(String description, String title, int difficulty, String tag) {
        this.description = description;
        this.title = title;
        this.difficulty = difficulty;
        this.tag = tag;
        upSet = new HashSet<>();
        pcomSet = new HashSet<>();
        pcomSet = new HashSet<>();
    }

    public Problem(int id,String description, String title, int difficulty, String tag) {
        this(description,title,difficulty,tag);
        this.problemId = id;
    }



    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<User_Problem> getUpSet() {
        return upSet;
    }

    public void setUpSet(Set<User_Problem> upSet) {
        this.upSet = upSet;
    }

    public Set<Problem_Company> getPcomSet() {
        return pcomSet;
    }

    public void setPcomSet(Set<Problem_Company> pcomSet) {
        this.pcomSet = pcomSet;
    }

    public Set<Problem_Code> getPcodeSet() {
        return pcodeSet;
    }

    public void setPcodeSet(Set<Problem_Code> pcodeSet) {
        this.pcodeSet = pcodeSet;
    }
}
