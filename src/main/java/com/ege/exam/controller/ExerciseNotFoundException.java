package com.ege.exam.controller;

public class ExerciseNotFoundException extends RuntimeException{
    public ExerciseNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
