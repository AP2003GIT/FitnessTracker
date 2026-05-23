package com.fitness.repository;

import com.fitness.entity.SetEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetEntryRepository extends JpaRepository<SetEntry, Long> {
}