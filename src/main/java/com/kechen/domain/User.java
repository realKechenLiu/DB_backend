package com.kechen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "isAdmin")
    private int isAdmin;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<User_Problem> upSet;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<Problem_Code> pcSet;

    public User() {
    }

    public User(String userName, String password, String email, int isAdmin) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        upSet = new HashSet<>();
        pcSet = new HashSet<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Set<User_Problem> getUpSet() {
        return upSet;
    }

    public void setUpSet(Set<User_Problem> upSet) {
        this.upSet = upSet;
    }

    public Set<Problem_Code> getPcSet() {
        return pcSet;
    }

    public void setPcSet(Set<Problem_Code> pcSet) {
        this.pcSet = pcSet;
    }
}
