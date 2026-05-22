<template>
  <div class="page">
    <div class="card">
      <h1>Fitness Tracker</h1>
      <p class="subtitle">
        Izračunaj koliko kalorija trošiš dnevno i kakva može biti promena kilaže.
      </p>

      <form @submit.prevent="calculateProgress" class="form">
        <div class="form-group">
          <label>Trenutna kilaža (kg)</label>
          <input
            type="number"
            v-model.number="form.currentWeightKg"
            placeholder="npr. 85"
            min="1"
            step="0.1"
            required
          />
        </div>

        <div class="form-group">
          <label>Broj sklekova dnevno</label>
          <input
            type="number"
            v-model.number="form.pushUps"
            placeholder="npr. 100"
            min="0"
          />
        </div>

        <div class="form-group">
          <label>Bicikl dnevno (minuta)</label>
          <input
            type="number"
            v-model.number="form.cyclingMinutes"
            placeholder="npr. 15"
            min="0"
          />
        </div>

        <div class="form-group">
          <label>Period računanja (dana)</label>
          <input
            type="number"
            v-model.number="form.days"
            placeholder="npr. 30"
            min="1"
          />
        </div>

        <button type="submit" :disabled="loading">
          {{ loading ? "Računam..." : "Izračunaj" }}
        </button>
      </form>

      <p v-if="errorMessage" class="error">
        {{ errorMessage }}
      </p>

      <div v-if="result" class="result">
        <h2>Rezultat</h2>

        <div class="result-grid">
          <div class="result-box">
            <span>Sklekovi</span>
            <strong>{{ result.pushUpsCalories }} kcal</strong>
          </div>

          <div class="result-box">
            <span>Bicikl</span>
            <strong>{{ result.cyclingCalories }} kcal</strong>
          </div>

          <div class="result-box highlight">
            <span>Dnevno ukupno</span>
            <strong>{{ result.dailyCaloriesBurned }} kcal</strong>
          </div>

          <div class="result-box">
            <span>Ukupno za {{ result.days }} dana</span>
            <strong>{{ result.totalCaloriesBurned }} kcal</strong>
          </div>

          <div class="result-box">
            <span>Procena gubitka</span>
            <strong>{{ result.estimatedWeightLossKg }} kg</strong>
          </div>

          <div class="result-box highlight">
            <span>Procena kilaže</span>
            <strong>{{ result.estimatedWeightAfterPeriodKg }} kg</strong>
          </div>
        </div>

        <p class="note">
          Napomena: rezultat je procena. Stvarna kilaža zavisi i od ishrane,
          sna, metabolizma i intenziteta treninga.
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",

  data() {
    return {
      form: {
        currentWeightKg: 85,
        pushUps: 100,
        cyclingMinutes: 15,
        days: 30
      },
      result: null,
      loading: false,
      errorMessage: ""
    };
  },

  methods: {
    async calculateProgress() {
      this.loading = true;
      this.errorMessage = "";
      this.result = null;

      try {
        const response = await fetch("http://localhost:8080/api/fitness/calculate", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(this.form)
        });

        if (!response.ok) {
          throw new Error("Greška pri računanju. Proveri unesene podatke.");
        }

        this.result = await response.json();
      } catch (error) {
        this.errorMessage = error.message || "Backend trenutno nije dostupan.";
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>
