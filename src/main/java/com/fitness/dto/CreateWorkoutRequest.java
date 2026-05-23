package com.fitness.dto;

import com.fitness.enums.MuscleGroup;
import com.fitness.enums.WorkoutIntensity;

import java.time.LocalDate;
import java.util.List;

public class CreateWorkoutRequest {

    private LocalDate workoutDate;
    private MuscleGroup muscleGroup;
    private Integer durationMinutes;
    private WorkoutIntensity intensity;
    private Double userWeightKg;
    private String notes;
    private List<ExerciseRequest> exercises;

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public WorkoutIntensity getIntensity() {
        return intensity;
    }

    public void setIntensity(WorkoutIntensity intensity) {
        this.intensity = intensity;
    }

    public Double getUserWeightKg() {
        return userWeightKg;
    }

    public void setUserWeightKg(Double userWeightKg) {
        this.userWeightKg = userWeightKg;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ExerciseRequest> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseRequest> exercises) {
        this.exercises = exercises;
    }
}