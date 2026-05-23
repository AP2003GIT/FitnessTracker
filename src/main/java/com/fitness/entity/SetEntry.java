package com.fitness.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "set_entries")
public class SetEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer setNumber;

    private Integer reps;

    private Double weightKg;

    private Integer durationSeconds;

    private Integer restSeconds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_entry_id")
    private ExerciseEntry exerciseEntry;

    public SetEntry() {
    }

    public Long getId() {
        return id;
    }

    public Integer getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(Integer setNumber) {
        this.setNumber = setNumber;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public Integer getRestSeconds() {
        return restSeconds;
    }

    public void setRestSeconds(Integer restSeconds) {
        this.restSeconds = restSeconds;
    }

    public ExerciseEntry getExerciseEntry() {
        return exerciseEntry;
    }

    public void setExerciseEntry(ExerciseEntry exerciseEntry) {
        this.exerciseEntry = exerciseEntry;
    }
}