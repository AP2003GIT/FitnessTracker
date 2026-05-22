# Fitness Tracker

Fitness Tracker is a full-stack web application for calculating daily calorie expenditure and estimating weight change based on physical activity.

The user enters their current weight, number of push-ups, cycling duration, and the number of days. The application calculates calories burned from push-ups and cycling, total calories burned over the selected period, estimated weight loss, and projected body weight.

## Features

- Enter current body weight
- Enter daily number of push-ups
- Enter daily cycling duration in minutes
- Choose calculation period in days
- Calculate:
  - Calories burned from push-ups
  - Calories burned from cycling
  - Total daily calories burned
  - Total calories burned over time
  - Estimated weight loss
  - Estimated weight after the selected period
- Spring Boot REST API backend
- Vue frontend
- One-click project startup using a `.bat` launcher

## Technologies Used

### Backend

- Java
- Spring Boot
- Maven
- REST API
- Jakarta Validation

### Frontend

- JavaScript
- Vue.js
- Vite
- HTML
- CSS

## Project Structure

```text
FitnessTracker
│
├── src
│   └── main
│       ├── java
│       │   └── com.fitness
│       │       ├── controller
│       │       │   └── FitnessController.java
│       │       ├── dto
│       │       │   ├── FitnessRequest.java
│       │       │   └── FitnessResponse.java
│       │       ├── service
│       │       │   └── FitnessCalculationService.java
│       │       └── FitnessTrackerApplication.java
│       │
│       └── resources
│           └── application.properties
│
├── frontend
│   ├── src
│   │   ├── App.vue
│   │   ├── main.js
│   │   └── style.css
│   └── package.json
│
├── pom.xml
├── mvnw.cmd
├── StartFitnessTracker.bat
└── README.md
