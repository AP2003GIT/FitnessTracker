package com.fitness.dto;

import java.util.List;

public class ExerciseRequest {

    private String exerciseName;
    private List<SetRequest> sets;

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public List<SetRequest> getSets() {
        return sets;
    }

    public void setSets(List<SetRequest> sets) {
        this.sets = sets;
    }
}