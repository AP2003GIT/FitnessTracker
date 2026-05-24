# Fitness Tracker

Fitness Tracker is a full-stack fitness application for creating and tracking detailed workout sessions.  
Users can log complete training sessions by adding muscle groups, exercises, sets, repetitions, weight, rest time, workout duration and intensity.

The backend automatically calculates workout statistics such as total exercises, total sets, total reps, total training volume and estimated calories burned.

---

## Project Description

This application is designed as a gym workout tracker.  
Instead of entering only basic workout data, users can create a full training session, for example:

- Back workout
  - Lat Pulldown
  - Pull Ups
  - Rows

- Leg workout
  - Squats
  - Bulgarian Split Squats
  - Leg Press

For each exercise, users can add multiple sets with reps, weight and rest time.

After saving the workout, the application returns a workout summary.

---

## Main Features

- Create full workout sessions
- Select muscle group
- Add multiple exercises per workout
- Add multiple sets per exercise
- Track reps, weight and rest time
- Track workout duration
- Select workout intensity
- Calculate total number of exercises
- Calculate total number of sets
- Calculate total number of reps
- Calculate total training volume
- Estimate calories burned
- Save workouts into PostgreSQL database
- Display workout summary on the frontend

---

## Technologies Used

### Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

### Frontend

- Vue.js
- JavaScript
- HTML
- CSS

### Database

- PostgreSQL
- pgAdmin 4

---

## Project Structure

```text
FitnessTracker
│
├── src
│   └── main
│       ├── java
│       │   └── com.fitness
│       │       ├── controller
│       │       │   └── WorkoutController.java
│       │       │
│       │       ├── dto
│       │       │   ├── CreateWorkoutRequest.java
│       │       │   ├── ExerciseRequest.java
│       │       │   ├── SetRequest.java
│       │       │   └── WorkoutSummaryResponse.java
│       │       │
│       │       ├── entity
│       │       │   ├── WorkoutSession.java
│       │       │   ├── ExerciseEntry.java
│       │       │   └── SetEntry.java
│       │       │
│       │       ├── enums
│       │       │   ├── MuscleGroup.java
│       │       │   └── WorkoutIntensity.java
│       │       │
│       │       ├── repository
│       │       │   ├── WorkoutSessionRepository.java
│       │       │   ├── ExerciseEntryRepository.java
│       │       │   └── SetEntryRepository.java
│       │       │
│       │       ├── service
│       │       │   └── WorkoutService.java
│       │       │
│       │       └── FitnessTrackerApplication.java
│       │
│       └── resources
│           └── application.properties
│
├── frontend
│   └── Vue frontend application
│
├── pom.xml
└── README.md
