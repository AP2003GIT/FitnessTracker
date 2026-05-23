package com.fitness.repository;

import com.fitness.entity.ExerciseEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseEntryRepository extends JpaRepository<ExerciseEntry, Long> {
}