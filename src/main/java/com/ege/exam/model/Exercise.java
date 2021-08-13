package com.ege.exam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Exercise {

    private @Id @GeneratedValue Long id;

    @Lob
    private String statement;
    private String answer;

    public Exercise() {
    }

    public Exercise(String statement, String answer) {
        this.statement = statement;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }

    public String getAnswer() {
        return answer;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
