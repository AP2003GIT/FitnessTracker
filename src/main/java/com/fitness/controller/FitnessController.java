package com.fitness.controller;

import com.fitness.dto.FitnessRequest;
import com.fitness.dto.FitnessResponse;
import com.fitness.service.FitnessCalculationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fitness")
@CrossOrigin(origins = "http://localhost:5173")
public class FitnessController {

    private final FitnessCalculationService fitnessCalculationService;

    public FitnessController(FitnessCalculationService fitnessCalculationService) {
        this.fitnessCalculationService = fitnessCalculationService;
    }

    @GetMapping("/test")
    public String test() {
        return "Fitness backend radi!";
    }

    @PostMapping("/calculate")
    public FitnessResponse calculate(@Valid @RequestBody FitnessRequest request) {
        return fitnessCalculationService.calculate(request);
    }
}