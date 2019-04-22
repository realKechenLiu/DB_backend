package com.kechen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "code")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code_id")
    private int codeId;

    @Column(name = "is_accepted")
    private int isAccepted;

    @Column(name = "performance")
    private double performance;

    @Column(name = "code_language")
    private String codeLanguage;

    @Column(name = "time_created")
    private Timestamp createTime;

    @Column(name = "time_modified")
    private Timestamp modeifyTime;

    @Column(name = "content",length = 62355)
    private String content;

    @JsonIgnore
    @OneToMany(mappedBy = "code",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<Problem_Code> pcCode;

    @OneToOne(mappedBy = "code")
    @JoinColumn(name = "id")
    private Note note;

    public Code() {
    }

    public Code(int isAccepted, double performance, String codeLanguage, Timestamp createTime, Timestamp modeifyTime, String content) {
        this.isAccepted = isAccepted;
        this.performance = performance;
        this.codeLanguage = codeLanguage;
        this.createTime = createTime;
        this.modeifyTime = modeifyTime;
        this.content = content;
        pcCode = new HashSet<>();
    }

    public Code(int codeId, int isAccepted, double performance, String codeLanguage, Timestamp createTime, Timestamp modeifyTime, String content) {
        this.codeId = codeId;
        this.isAccepted = isAccepted;
        this.performance = performance;
        this.codeLanguage = codeLanguage;
        this.createTime = createTime;
        this.modeifyTime = modeifyTime;
        this.content = content;
        pcCode = new HashSet<>();
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public int getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(int isAccepted) {
        this.isAccepted = isAccepted;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public String getCodeLanguage() {
        return codeLanguage;
    }

    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModeifyTime() {
        return modeifyTime;
    }

    public void setModeifyTime(Timestamp modeifyTime) {
        this.modeifyTime = modeifyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Problem_Code> getPcCode() {
        return pcCode;
    }

    public void setPcCode(Set<Problem_Code> pcCode) {
        this.pcCode = pcCode;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}

