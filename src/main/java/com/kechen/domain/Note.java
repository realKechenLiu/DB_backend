package com.kechen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    private int noteId;

    @Column(name = "content")
    private String content;

    @Column(name = "time_created")
    private Timestamp createTime;

    @Column(name = "time_modified")
    private Timestamp modifyTime;

    @OneToOne(cascade =  CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "code_id")
    private Code code;

    public Note() {
    }

    public Note(String content, Timestamp createTime, Timestamp modifyTime, Code code) {
        this.content = content;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.code = code;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getmodifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modefyTime) {
        this.modifyTime = modefyTime;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
