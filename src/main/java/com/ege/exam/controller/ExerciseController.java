package com.ege.exam.controller;

import com.ege.exam.model.Exercise;
import com.ege.exam.repository.ExerciseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;

    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping("/exercises")
    List<Exercise> all() {
        return exerciseRepository.findAll();
    }

    @PostMapping("/exercises")
    Exercise newExercise(@RequestBody Exercise newExercise) {
        return exerciseRepository.save(newExercise);
    }

    @GetMapping("/exercise/{id}")
    Exercise findByIdExercise(@PathVariable Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException(id));
    }

    @PutMapping("/exercises/{id}")
    Exercise replaceExercise(@RequestBody Exercise newExercise, @PathVariable Long id) {
        return exerciseRepository.findById(id)
                .map(exercise -> {
                    exercise.setStatement(newExercise.getStatement());
                    exercise.setAnswer(newExercise.getAnswer());
                    return exerciseRepository.save(exercise);
                })
                .orElseGet(() -> {
                    newExercise.setId(id);
                    return exerciseRepository.save(newExercise);
                });
    }

    @DeleteMapping("/exercise/{id}")
    void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

}
