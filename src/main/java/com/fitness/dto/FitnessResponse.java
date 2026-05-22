package com.fitness.dto;

public class FitnessResponse {

    private double pushUpsCalories;
    private double cyclingCalories;
    private double dailyCaloriesBurned;
    private double totalCaloriesBurned;
    private double estimatedWeightLossKg;
    private double estimatedWeightAfterPeriodKg;
    private int days;

    public FitnessResponse() {
    }

    public FitnessResponse(
            double pushUpsCalories,
            double cyclingCalories,
            double dailyCaloriesBurned,
            double totalCaloriesBurned,
            double estimatedWeightLossKg,
            double estimatedWeightAfterPeriodKg,
            int days
    ) {
        this.pushUpsCalories = pushUpsCalories;
        this.cyclingCalories = cyclingCalories;
        this.dailyCaloriesBurned = dailyCaloriesBurned;
        this.totalCaloriesBurned = totalCaloriesBurned;
        this.estimatedWeightLossKg = estimatedWeightLossKg;
        this.estimatedWeightAfterPeriodKg = estimatedWeightAfterPeriodKg;
        this.days = days;
    }

    public double getPushUpsCalories() {
        return pushUpsCalories;
    }

    public void setPushUpsCalories(double pushUpsCalories) {
        this.pushUpsCalories = pushUpsCalories;
    }

    public double getCyclingCalories() {
        return cyclingCalories;
    }

    public void setCyclingCalories(double cyclingCalories) {
        this.cyclingCalories = cyclingCalories;
    }

    public double getDailyCaloriesBurned() {
        return dailyCaloriesBurned;
    }

    public void setDailyCaloriesBurned(double dailyCaloriesBurned) {
        this.dailyCaloriesBurned = dailyCaloriesBurned;
    }

    public double getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    public void setTotalCaloriesBurned(double totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public double getEstimatedWeightLossKg() {
        return estimatedWeightLossKg;
    }

    public void setEstimatedWeightLossKg(double estimatedWeightLossKg) {
        this.estimatedWeightLossKg = estimatedWeightLossKg;
    }

    public double getEstimatedWeightAfterPeriodKg() {
        return estimatedWeightAfterPeriodKg;
    }

    public void setEstimatedWeightAfterPeriodKg(double estimatedWeightAfterPeriodKg) {
        this.estimatedWeightAfterPeriodKg = estimatedWeightAfterPeriodKg;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}