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
}
