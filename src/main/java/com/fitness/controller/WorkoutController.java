package com.fitness.controller;

import com.fitness.dto.CreateWorkoutRequest;
import com.fitness.dto.WorkoutSummaryResponse;
import com.fitness.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin(origins = "*")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<WorkoutSummaryResponse> createWorkout(@RequestBody CreateWorkoutRequest request) {
        WorkoutSummaryResponse response = workoutService.createWorkout(request);
        return ResponseEntity.ok(response);
    }
}