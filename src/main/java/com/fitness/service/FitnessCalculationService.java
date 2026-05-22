package com.fitness.service;

import com.fitness.dto.FitnessRequest;
import com.fitness.dto.FitnessResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class FitnessCalculationService {

    private static final double PUSH_UPS_MET = 8.0;
    private static final double CYCLING_MET = 6.8;
    private static final double CALORIES_PER_KG_FAT = 7700.0;
    private static final double MINUTES_PER_PUSH_UP = 0.1;

    public FitnessResponse calculate(FitnessRequest request) {
        double currentWeightKg = request.getCurrentWeightKg();

        int pushUps = request.getPushUps() != null ? request.getPushUps() : 0;
        int cyclingMinutes = request.getCyclingMinutes() != null ? request.getCyclingMinutes() : 0;
        int days = request.getDays() != null ? request.getDays() : 30;

        double pushUpsMinutes = pushUps * MINUTES_PER_PUSH_UP;

        double pushUpsCalories = calculateCalories(PUSH_UPS_MET, currentWeightKg, pushUpsMinutes);
        double cyclingCalories = calculateCalories(CYCLING_MET, currentWeightKg, cyclingMinutes);

        double dailyCaloriesBurned = pushUpsCalories + cyclingCalories;
        double totalCaloriesBurned = dailyCaloriesBurned * days;

        double estimatedWeightLossKg = totalCaloriesBurned / CALORIES_PER_KG_FAT;
        double estimatedWeightAfterPeriodKg = currentWeightKg - estimatedWeightLossKg;

        return new FitnessResponse(
                round(pushUpsCalories),
                round(cyclingCalories),
                round(dailyCaloriesBurned),
                round(totalCaloriesBurned),
                round(estimatedWeightLossKg),
                round(estimatedWeightAfterPeriodKg),
                days
        );
    }

    private double calculateCalories(double met, double weightKg, double minutes) {
        return met * 3.5 * weightKg * minutes / 200;
    }

    private double round(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}