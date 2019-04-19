package com.kechen.IdClasses;

import com.kechen.domain.Problem;
import com.kechen.domain.User;

import java.io.Serializable;
import java.util.Objects;

public class UPId implements Serializable {

    private User user;
    private Problem problem;

    public UPId() {
    }

    public UPId(User user, Problem problem) {
        this.user = user;
        this.problem = problem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UPId)) return false;
        UPId upId = (UPId) o;
        return Objects.equals(user, upId.user) &&
                Objects.equals(problem, upId.problem);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, problem);
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
}
