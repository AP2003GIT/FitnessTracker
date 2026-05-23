package com.fitness.repository;

import com.fitness.entity.WorkoutSession;
import com.fitness.enums.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {

    List<WorkoutSession> findByMuscleGroup(MuscleGroup muscleGroup);

    List<WorkoutSession> findByWorkoutDate(LocalDate workoutDate);

    List<WorkoutSession> findTop2ByMuscleGroupOrderByWorkoutDateDesc(MuscleGroup muscleGroup);
}