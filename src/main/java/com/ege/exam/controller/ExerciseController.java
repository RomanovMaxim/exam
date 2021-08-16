package com.ege.exam.controller;

import com.ege.exam.model.Exercise;
import com.ege.exam.repository.ExerciseRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;

    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping("/exercises")
    CollectionModel<EntityModel<Exercise>> all() {

        List<EntityModel<Exercise>> exercises = exerciseRepository.findAll().stream()
                .map(exercise -> EntityModel.of(exercise,
                        linkTo(methodOn(ExerciseController.class).findByIdExercise(exercise.getId())).withSelfRel(),
                        linkTo(methodOn(ExerciseController.class).all()).withRel("exercises")
                        ))
                .collect(Collectors.toList());

        return CollectionModel.of(exercises,
                linkTo(methodOn(ExerciseController.class).all()).withSelfRel());
    }

    @PostMapping("/exercises")
    Exercise newExercise(@RequestBody Exercise newExercise) {
        return exerciseRepository.save(newExercise);
    }

    @GetMapping("/exercise/{id}")
    EntityModel<Exercise> findByIdExercise(@PathVariable Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException(id));

        return EntityModel.of(exercise,
                linkTo(methodOn(ExerciseController.class).findByIdExercise(id)).withSelfRel(),
                linkTo(methodOn(ExerciseController.class).all()).withRel("exercises")
        );
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
