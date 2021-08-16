package com.ege.exam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Objects;

@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String statement;

    private String answer;

    private int port;


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

    public int getPort() {
        return port;
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

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(id, exercise.id) && Objects.equals(statement, exercise.statement) && Objects.equals(answer, exercise.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statement, answer);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", statement='" + statement + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
