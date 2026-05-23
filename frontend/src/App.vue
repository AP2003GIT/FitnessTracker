<template>
  <div class="page">
    <div class="container">
      <h1>Fitness Tracker</h1>
      <p class="subtitle">Create a full workout session with exercises, sets, reps and weight.</p>

      <form class="card" @submit.prevent="saveWorkout">
        <div class="grid">
          <div>
            <label>Workout date</label>
            <input type="date" v-model="workout.workoutDate" />
          </div>

          <div>
            <label>Muscle group</label>
            <select v-model="workout.muscleGroup">
              <option value="CHEST">Chest</option>
              <option value="BACK">Back</option>
              <option value="LEGS">Legs</option>
              <option value="SHOULDERS">Shoulders</option>
              <option value="ARMS">Arms</option>
              <option value="CORE">Core</option>
              <option value="FULL_BODY">Full body</option>
            </select>
          </div>

          <div>
            <label>Duration minutes</label>
            <input type="number" v-model.number="workout.durationMinutes" min="1" />
          </div>

          <div>
            <label>Intensity</label>
            <select v-model="workout.intensity">
              <option value="LOW">Low</option>
              <option value="MEDIUM">Medium</option>
              <option value="HIGH">High</option>
            </select>
          </div>

          <div>
            <label>Your weight kg</label>
            <input type="number" v-model.number="workout.userWeightKg" min="1" />
          </div>
        </div>

        <div>
          <label>Notes</label>
          <textarea v-model="workout.notes" placeholder="Example: Good back workout"></textarea>
        </div>

        <hr />

        <h2>Exercises</h2>

        <div
          class="exercise"
          v-for="(exercise, exerciseIndex) in workout.exercises"
          :key="exerciseIndex"
        >
          <div class="exercise-header">
            <input
              class="exercise-name"
              v-model="exercise.exerciseName"
              placeholder="Exercise name, e.g. Lat Pulldown"
            />

            <button type="button" class="danger" @click="removeExercise(exerciseIndex)">
              Remove exercise
            </button>
          </div>

          <div class="sets">
            <div
              class="set-row"
              v-for="(set, setIndex) in exercise.sets"
              :key="setIndex"
            >
              <span class="set-number">Set {{ setIndex + 1 }}</span>

              <input
                type="number"
                v-model.number="set.reps"
                placeholder="Reps"
                min="0"
              />

              <input
                type="number"
                v-model.number="set.weightKg"
                placeholder="Kg"
                min="0"
              />

              <input
                type="number"
                v-model.number="set.restSeconds"
                placeholder="Rest sec"
                min="0"
              />

              <button type="button" class="small-danger" @click="removeSet(exerciseIndex, setIndex)">
                X
              </button>
            </div>
          </div>

          <button type="button" class="secondary" @click="addSet(exerciseIndex)">
            + Add set
          </button>
        </div>

        <button type="button" class="secondary full" @click="addExercise">
          + Add exercise
        </button>

        <button type="submit" class="primary full">
          Save workout
        </button>
      </form>

      <div v-if="summary" class="summary card">
        <h2>Workout summary</h2>

        <div class="summary-grid">
          <div>
            <span>Muscle group</span>
            <strong>{{ summary.muscleGroup }}</strong>
          </div>

          <div>
            <span>Duration</span>
            <strong>{{ summary.durationMinutes }} min</strong>
          </div>

          <div>
            <span>Total exercises</span>
            <strong>{{ summary.totalExercises }}</strong>
          </div>

          <div>
            <span>Total sets</span>
            <strong>{{ summary.totalSets }}</strong>
          </div>

          <div>
            <span>Total reps</span>
            <strong>{{ summary.totalReps }}</strong>
          </div>

          <div>
            <span>Total volume</span>
            <strong>{{ summary.totalVolumeKg }} kg</strong>
          </div>

          <div>
            <span>Estimated calories</span>
            <strong>{{ summary.estimatedCalories }} kcal</strong>
          </div>
        </div>

        <p class="message">{{ summary.message }}</p>
      </div>

      <p v-if="errorMessage" class="error">
        {{ errorMessage }}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",

  data() {
    return {
      workout: {
        workoutDate: new Date().toISOString().split("T")[0],
        muscleGroup: "BACK",
        durationMinutes: 60,
        intensity: "MEDIUM",
        userWeightKg: 80,
        notes: "",
        exercises: [
          {
            exerciseName: "Lat Pulldown",
            sets: [
              {
                setNumber: 1,
                reps: 12,
                weightKg: 50,
                durationSeconds: null,
                restSeconds: 90
              }
            ]
          }
        ]
      },
      summary: null,
      errorMessage: ""
    };
  },

  methods: {
    addExercise() {
      this.workout.exercises.push({
        exerciseName: "",
        sets: [
          {
            setNumber: 1,
            reps: null,
            weightKg: null,
            durationSeconds: null,
            restSeconds: null
          }
        ]
      });
    },

    removeExercise(index) {
      this.workout.exercises.splice(index, 1);
    },

    addSet(exerciseIndex) {
      const sets = this.workout.exercises[exerciseIndex].sets;

      sets.push({
        setNumber: sets.length + 1,
        reps: null,
        weightKg: null,
        durationSeconds: null,
        restSeconds: null
      });
    },

    removeSet(exerciseIndex, setIndex) {
      const sets = this.workout.exercises[exerciseIndex].sets;
      sets.splice(setIndex, 1);

      sets.forEach((set, index) => {
        set.setNumber = index + 1;
      });
    },

    async saveWorkout() {
      this.errorMessage = "";
      this.summary = null;

      this.fixSetNumbers();

      try {
        const response = await fetch("http://localhost:8080/api/workouts", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(this.workout)
        });

        if (!response.ok) {
          throw new Error("Backend returned error");
        }

        this.summary = await response.json();
      } catch (error) {
        this.errorMessage = "Workout was not saved. Check if backend is running on port 8080.";
        console.error(error);
      }
    },

    fixSetNumbers() {
      this.workout.exercises.forEach((exercise) => {
        exercise.sets.forEach((set, index) => {
          set.setNumber = index + 1;
        });
      });
    }
  }
};
</script>

<style>
* {
  box-sizing: border-box;
}

body {
  margin: 0;
  font-family: Arial, sans-serif;
  background: #f4f6f8;
  color: #1f2937;
}

.page {
  min-height: 100vh;
  padding: 40px 20px;
}

.container {
  max-width: 1000px;
  margin: 0 auto;
}

h1 {
  margin-bottom: 5px;
  font-size: 36px;
}

.subtitle {
  margin-bottom: 25px;
  color: #6b7280;
}

.card {
  background: white;
  border-radius: 16px;
  padding: 24px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
  margin-bottom: 24px;
}

.grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

label {
  display: block;
  margin-bottom: 6px;
  font-weight: bold;
}

input,
select,
textarea {
  width: 100%;
  padding: 11px;
  border: 1px solid #d1d5db;
  border-radius: 10px;
  font-size: 15px;
}

textarea {
  min-height: 80px;
  resize: vertical;
}

hr {
  margin: 24px 0;
  border: none;
  border-top: 1px solid #e5e7eb;
}

.exercise {
  border: 1px solid #e5e7eb;
  border-radius: 14px;
  padding: 16px;
  margin-bottom: 16px;
  background: #fafafa;
}

.exercise-header {
  display: flex;
  gap: 12px;
  margin-bottom: 14px;
}

.exercise-name {
  flex: 1;
}

.set-row {
  display: grid;
  grid-template-columns: 80px 1fr 1fr 1fr 45px;
  gap: 10px;
  align-items: center;
  margin-bottom: 10px;
}

.set-number {
  font-weight: bold;
}

button {
  border: none;
  cursor: pointer;
  border-radius: 10px;
  padding: 11px 14px;
  font-weight: bold;
}

.primary {
  background: #2563eb;
  color: white;
  margin-top: 16px;
}

.secondary {
  background: #e5e7eb;
  color: #111827;
}

.danger,
.small-danger {
  background: #ef4444;
  color: white;
}

.full {
  width: 100%;
  margin-top: 10px;
}

.summary-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 15px;
}

.summary-grid div {
  background: #f9fafb;
  border-radius: 12px;
  padding: 14px;
}

.summary-grid span {
  display: block;
  color: #6b7280;
  margin-bottom: 5px;
}

.summary-grid strong {
  font-size: 20px;
}

.message {
  margin-top: 18px;
  font-weight: bold;
}

.error {
  color: #dc2626;
  font-weight: bold;
}

@media (max-width: 700px) {
  .grid,
  .summary-grid {
    grid-template-columns: 1fr;
  }

  .exercise-header {
    flex-direction: column;
  }

  .set-row {
    grid-template-columns: 1fr;
  }
}
</style>