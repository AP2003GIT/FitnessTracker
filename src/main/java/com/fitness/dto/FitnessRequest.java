package com.fitness.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class FitnessRequest {

    @NotNull(message = "Current weight is required.")
    @Positive(message = "Current weight must be greater than 0.")
    private Double currentWeightKg;

    @Min(value = 0, message = "Push-ups cannot be negative.")
    private Integer pushUps;

    @Min(value = 0, message = "Cycling minutes cannot be negative.")
    private Integer cyclingMinutes;

    @Min(value = 1, message = "Days must be at least 1.")
    private Integer days = 30;

    public Double getCurrentWeightKg() {
        return currentWeightKg;
    }

    public void setCurrentWeightKg(Double currentWeightKg) {
        this.currentWeightKg = currentWeightKg;
    }

    public Integer getPushUps() {
        return pushUps;
    }

    public void setPushUps(Integer pushUps) {
        this.pushUps = pushUps;
    }

    public Integer getCyclingMinutes() {
        return cyclingMinutes;
    }

    public void setCyclingMinutes(Integer cyclingMinutes) {
        this.cyclingMinutes = cyclingMinutes;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}