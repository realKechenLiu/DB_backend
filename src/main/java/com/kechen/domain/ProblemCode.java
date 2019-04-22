package com.kechen.domain;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class ProblemCode {

    public int problemId;
    public String description;
    public String title;
    public int difficulty;
    public String tag;

    public List<String> companyList;
    public List<Code> codeList;

    public ProblemCode() {
        companyList = new LinkedList<>();
        codeList = new LinkedList<>();
    }
}


