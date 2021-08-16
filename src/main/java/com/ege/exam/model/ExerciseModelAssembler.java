package com.ege.exam.model;

import com.ege.exam.controller.ExerciseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ExerciseModelAssembler implements RepresentationModelAssembler<Exercise, EntityModel<Exercise>> {

    @Autowired
    private Environment environment;

    @Override
    public EntityModel<Exercise> toModel(Exercise exercise) {
        exercise.setPort(
                Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port")))
        );
        return EntityModel.of(exercise,
                linkTo(methodOn(ExerciseController.class).findByIdExercise(exercise.getId())).withSelfRel(),
                linkTo(methodOn(ExerciseController.class).all()).withRel("exercises"));
    }
}
