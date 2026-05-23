package com.fitness.entity;

import com.fitness.enums.MuscleGroup;
import com.fitness.enums.WorkoutIntensity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workout_sessions")
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate workoutDate;

    @Enumerated(EnumType.STRING)
    private MuscleGroup muscleGroup;

    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    private WorkoutIntensity intensity;

    private String notes;

    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseEntry> exercises = new ArrayList<>();

    public WorkoutSession() {
    }

    public Long getId() {
        return id;
    }

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ExerciseEntry> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseEntry> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(ExerciseEntry exercise) {
        exercises.add(exercise);
        exercise.setWorkoutSession(this);
    }
}