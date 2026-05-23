package com.fitness.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise_entries")
public class ExerciseEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String exerciseName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_session_id")
    private WorkoutSession workoutSession;

    @OneToMany(mappedBy = "exerciseEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SetEntry> sets = new ArrayList<>();

    public ExerciseEntry() {
    }

    public Long getId() {
        return id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public WorkoutSession getWorkoutSession() {
        return workoutSession;
    }

    public void setWorkoutSession(WorkoutSession workoutSession) {
        this.workoutSession = workoutSession;
    }

    public List<SetEntry> getSets() {
        return sets;
    }

    public void setSets(List<SetEntry> sets) {
        this.sets = sets;
    }

    public void addSet(SetEntry setEntry) {
        sets.add(setEntry);
        setEntry.setExerciseEntry(this);
    }
}