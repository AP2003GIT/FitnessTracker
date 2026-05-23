package com.fitness.service;

import com.fitness.dto.CreateWorkoutRequest;
import com.fitness.dto.ExerciseRequest;
import com.fitness.dto.SetRequest;
import com.fitness.dto.WorkoutSummaryResponse;
import com.fitness.entity.ExerciseEntry;
import com.fitness.entity.SetEntry;
import com.fitness.entity.WorkoutSession;
import com.fitness.enums.WorkoutIntensity;
import com.fitness.repository.WorkoutSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WorkoutService {

    private final WorkoutSessionRepository workoutSessionRepository;

    public WorkoutService(WorkoutSessionRepository workoutSessionRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
    }

    public WorkoutSummaryResponse createWorkout(CreateWorkoutRequest request) {
        WorkoutSession workoutSession = new WorkoutSession();

        workoutSession.setWorkoutDate(
                request.getWorkoutDate() != null ? request.getWorkoutDate() : LocalDate.now()
        );

        workoutSession.setMuscleGroup(request.getMuscleGroup());
        workoutSession.setDurationMinutes(request.getDurationMinutes());
        workoutSession.setIntensity(request.getIntensity());
        workoutSession.setNotes(request.getNotes());

        if (request.getExercises() != null) {
            for (ExerciseRequest exerciseRequest : request.getExercises()) {
                ExerciseEntry exerciseEntry = new ExerciseEntry();
                exerciseEntry.setExerciseName(exerciseRequest.getExerciseName());

                if (exerciseRequest.getSets() != null) {
                    for (SetRequest setRequest : exerciseRequest.getSets()) {
                        SetEntry setEntry = new SetEntry();

                        setEntry.setSetNumber(setRequest.getSetNumber());
                        setEntry.setReps(setRequest.getReps());
                        setEntry.setWeightKg(setRequest.getWeightKg());
                        setEntry.setDurationSeconds(setRequest.getDurationSeconds());
                        setEntry.setRestSeconds(setRequest.getRestSeconds());

                        exerciseEntry.addSet(setEntry);
                    }
                }

                workoutSession.addExercise(exerciseEntry);
            }
        }

        WorkoutSession savedWorkout = workoutSessionRepository.save(workoutSession);

        return buildSummary(savedWorkout, request.getUserWeightKg());
    }

    private WorkoutSummaryResponse buildSummary(WorkoutSession workoutSession, Double userWeightKg) {
        int totalExercises = workoutSession.getExercises().size();
        int totalSets = 0;
        int totalReps = 0;
        double totalVolumeKg = 0.0;

        for (ExerciseEntry exercise : workoutSession.getExercises()) {
            totalSets += exercise.getSets().size();

            for (SetEntry set : exercise.getSets()) {
                int reps = set.getReps() != null ? set.getReps() : 0;
                double weight = set.getWeightKg() != null ? set.getWeightKg() : 0.0;

                totalReps += reps;
                totalVolumeKg += reps * weight;
            }
        }

        double estimatedCalories = calculateCalories(
                workoutSession.getDurationMinutes(),
                userWeightKg,
                workoutSession.getIntensity()
        );

        String message = "Workout saved successfully. Total volume: " + totalVolumeKg + " kg.";

        return new WorkoutSummaryResponse(
                workoutSession.getId(),
                workoutSession.getWorkoutDate(),
                workoutSession.getMuscleGroup(),
                workoutSession.getDurationMinutes(),
                totalExercises,
                totalSets,
                totalReps,
                totalVolumeKg,
                estimatedCalories,
                message
        );
    }

    private double calculateCalories(Integer durationMinutes, Double userWeightKg, WorkoutIntensity intensity) {
        if (durationMinutes == null || userWeightKg == null || intensity == null) {
            return 0.0;
        }

        double factor;

        switch (intensity) {
            case LOW:
                factor = 0.06;
                break;
            case MEDIUM:
                factor = 0.08;
                break;
            case HIGH:
                factor = 0.10;
                break;
            default:
                factor = 0.08;
        }

        return durationMinutes * userWeightKg * factor;
    }
}