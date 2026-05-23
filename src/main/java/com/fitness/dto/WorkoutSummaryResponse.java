package com.fitness.dto;

import com.fitness.enums.MuscleGroup;

import java.time.LocalDate;

public class WorkoutSummaryResponse {

    private Long id;
    private LocalDate workoutDate;
    private MuscleGroup muscleGroup;
    private Integer durationMinutes;
    private Integer totalExercises;
    private Integer totalSets;
    private Integer totalReps;
    private Double totalVolumeKg;
    private Double estimatedCalories;
    private String message;

    public WorkoutSummaryResponse() {
    }

    public WorkoutSummaryResponse(Long id, LocalDate workoutDate, MuscleGroup muscleGroup, Integer durationMinutes,
                                  Integer totalExercises, Integer totalSets, Integer totalReps,
                                  Double totalVolumeKg, Double estimatedCalories, String message) {
        this.id = id;
        this.workoutDate = workoutDate;
        this.muscleGroup = muscleGroup;
        this.durationMinutes = durationMinutes;
        this.totalExercises = totalExercises;
        this.totalSets = totalSets;
        this.totalReps = totalReps;
        this.totalVolumeKg = totalVolumeKg;
        this.estimatedCalories = estimatedCalories;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public Integer getTotalExercises() {
        return totalExercises;
    }

    public Integer getTotalSets() {
        return totalSets;
    }

    public Integer getTotalReps() {
        return totalReps;
    }

    public Double getTotalVolumeKg() {
        return totalVolumeKg;
    }

    public Double getEstimatedCalories() {
        return estimatedCalories;
    }

    public String getMessage() {
        return message;
    }
}