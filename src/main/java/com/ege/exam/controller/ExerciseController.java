package com.ege.exam.controller;

import com.ege.exam.model.Exercise;
import com.ege.exam.model.ExerciseModelAssembler;
import com.ege.exam.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseModelAssembler assembler;

    public ExerciseController(ExerciseRepository exerciseRepository,
                              ExerciseModelAssembler assembler) {
        this.exerciseRepository = exerciseRepository;
        this.assembler = assembler;
    }

    @GetMapping("/exercises")
    public CollectionModel<EntityModel<Exercise>> all() {

        List<EntityModel<Exercise>> exercises = exerciseRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(exercises,
                linkTo(methodOn(ExerciseController.class).all()).withSelfRel());
    }

    @PostMapping("/exercises")
    ResponseEntity<?> newExercise(@RequestBody Exercise newExercise) {

        EntityModel<Exercise> entityModel = assembler.toModel(exerciseRepository.save(newExercise));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/exercise/{id}")
    public EntityModel<Exercise> findByIdExercise(@PathVariable Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException(id));

        return assembler.toModel(exercise);
    }

    @PutMapping("/exercises/{id}")
    ResponseEntity<?> replaceExercise(@RequestBody Exercise newExercise, @PathVariable Long id) {

        Exercise updatedExercise = exerciseRepository.findById(id)
                .map(exercise -> {
                    exercise.setStatement(newExercise.getStatement());
                    exercise.setAnswer(newExercise.getAnswer());
                    return exerciseRepository.save(exercise);
                })
                .orElseGet(() -> {
                    newExercise.setId(id);
                    return exerciseRepository.save(newExercise);
                });

        EntityModel<Exercise> entityModel = assembler.toModel(updatedExercise);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/exercise/{id}")
    ResponseEntity<?> deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
