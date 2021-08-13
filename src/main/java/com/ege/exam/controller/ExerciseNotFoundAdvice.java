package com.ege.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExerciseNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ExerciseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String exerciseNotFoundHandler(ExerciseNotFoundException exception) {
        return exception.getMessage();
    }

}
