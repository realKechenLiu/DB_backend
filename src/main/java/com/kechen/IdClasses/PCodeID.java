package com.kechen.IdClasses;

import com.kechen.domain.Code;
import com.kechen.domain.Problem;
import com.kechen.domain.User;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

public class PCodeID implements Serializable {

    private Problem problem;
    private Code code;
    private User user;

    public PCodeID() {
    }

    public PCodeID(Problem problem, Code code, User user) {
        this.problem = problem;
        this.code = code;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PCodeID)) return false;
        PCodeID pCodeID = (PCodeID) o;
        return Objects.equals(problem, pCodeID.problem) &&
                Objects.equals(code, pCodeID.code) &&
                Objects.equals(user, pCodeID.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(problem, code, user);
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

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
}
