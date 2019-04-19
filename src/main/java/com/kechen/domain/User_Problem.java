package com.kechen.domain;

import com.kechen.IdClasses.UPId;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "user_problem")
@IdClass(UPId.class)
public class User_Problem implements Serializable{

    @Id
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(name = "is_finished")
    private int isFinished;

    public User_Problem() {
    }

    public User_Problem(User user, Problem problem, int isFinished) {
        this.user = user;
        this.problem = problem;
        this.isFinished = isFinished;
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

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }
}
