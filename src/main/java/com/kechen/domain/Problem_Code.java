package com.kechen.domain;

import com.kechen.IdClasses.PCodeID;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(PCodeID.class)
public class Problem_Code implements Serializable{

    @Id
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Id
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "code_id")
    private Code code;

    @Id
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Problem_Code() {
    }

    public Problem_Code(Problem problem, Code code, User user) {
        this.problem = problem;
        this.code = code;
        this.user = user;
    }
}
