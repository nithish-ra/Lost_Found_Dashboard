<template>
  <BarNav />
  
  <div class="register-container">
    <div class="register-background">
      <div class="floating-shape shape-1"></div>
      <div class="floating-shape shape-2"></div>
      <div class="floating-shape shape-3"></div>
    </div>

    <div class="register-card">
      <div class="card-header">
        <div class="header-icon">✨</div>
        <h2 class="card-title">Create Account</h2>
        <p class="card-subtitle">Join our community and start posting today</p>
      </div>

      <form @submit.prevent="saveUser" class="register-form">
        <div class="form-group">
          <label for="name" class="form-label">
            <span class="label-icon">👤</span>
            <span>Full Name</span>
          </label>
          <input
            v-model="user.name"
            id="name"
            placeholder="John Doe"
            required
            class="form-input"
          />
        </div>

        <div class="form-group">
          <label for="email" class="form-label">
            <span class="label-icon">📧</span>
            <span>Email Address</span>
          </label>
          <input
            v-model="user.email"
            id="email"
            type="email"
            placeholder="john@example.com"
            required
            class="form-input"
          />
        </div>

        <div class="form-group">
          <label for="phone" class="form-label">
            <span class="label-icon">📱</span>
            <span>Phone Number</span>
          </label>
          <input
            v-model="user.phone"
            id="phone"
            type="tel"
            placeholder="+91 9876543211"
            required
            class="form-input"
          />
        </div>

        <div class="form-group">
          <label for="password" class="form-label">
            <span class="label-icon">🔒</span>
            <span>Password</span>
          </label>
          <input
            v-model="user.password"
            id="password"
            type="password"
            placeholder="Create a strong password"
            required
            class="form-input"
          />
          <p class="input-hint">At least 6 characters recommended</p>
        </div>

        <button
          type="submit"
          :disabled="!isValid"
          class="register-button"
          :class="{ 'button-disabled': !isValid }"
        >
          <span class="button-text">Create Account</span>
          <span class="button-icon">→</span>
        </button>

        <div class="login-prompt">
          <p>Already have an account?</p>
          <router-link to="/login" class="login-link">Sign in here</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useUserStore } from "../store/userstore";
import BarNav from "../components/BarNav.vue";

const store = useUserStore();
const user = ref({ name: "", email: "", phone: "", password: "" });

const isValid = computed(() =>
  user.value.name && user.value.email && user.value.phone && user.value.password
);

const saveUser = async () => {
  await store.createUser(user.value);
  alert("Account created successfully! You can now sign in.");
  user.value = { name: "", email: "", phone: "", password: "" };
};
</script>

<style scoped>
.register-container {
  min-height: calc(100vh - 80px);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2rem 1rem;
  position: relative;
  overflow: hidden;
}

.register-background {
  position: absolute;
  inset: 0;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  z-index: 0;
}

.floating-shape {
  position: absolute;
  border-radius: 50%;
  opacity: 0.1;
  animation: float 20s infinite ease-in-out;
}

.shape-1 {
  width: 300px;
  height: 300px;
  background: white;
  top: -100px;
  left: -100px;
  animation-delay: 0s;
}

.shape-2 {
  width: 250px;
  height: 250px;
  background: white;
  bottom: -80px;
  right: -80px;
  animation-delay: 7s;
}

.shape-3 {
  width: 180px;
  height: 180px;
  background: white;
  top: 40%;
  right: 15%;
  animation-delay: 14s;
}

@keyframes float {
  0%, 100% {
    transform: translate(0, 0) rotate(0deg);
  }
  33% {
    transform: translate(30px, -30px) rotate(120deg);
  }
  66% {
    transform: translate(-20px, 20px) rotate(240deg);
  }
}

.register-card {
  width: 100%;
  max-width: 520px;
  background: white;
  border-radius: 24px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
  position: relative;
  z-index: 1;
  animation: slideUp 0.6s ease-out;
}

@keyframes slideUp {
  from {
    opacity: 0;
    transform: translateY(40px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.card-header {
  padding: 3rem 2rem 2rem;
  text-align: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 24px 24px 0 0;
  color: white;
}

.header-icon {
  font-size: 4rem;
  margin-bottom: 1rem;
  animation: sparkle 2s infinite;
}

@keyframes sparkle {
  0%, 100% {
    transform: scale(1) rotate(0deg);
  }
  50% {
    transform: scale(1.1) rotate(10deg);
  }
}

.card-title {
  font-size: 2rem;
  font-weight: 800;
  margin-bottom: 0.5rem;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card-subtitle {
  font-size: 1rem;
  opacity: 0.95;
  font-weight: 300;
}

.register-form {
  padding: 2.5rem 2rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

.form-label {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.95rem;
  font-weight: 600;
  color: #374151;
  margin-bottom: 0.5rem;
}

.label-icon {
  font-size: 1.2rem;
}

.form-input {
  width: 90%;
  padding: 1rem 1.25rem;
  border: 2px solid #e5e7eb;
  border-radius: 12px;
  font-size: 1rem;
  transition: all 0.3s ease;
  background: #f9fafb;
  outline: none;
}

.form-input:focus {
  border-color: #667eea;
  background: white;
  box-shadow: 0 0 0 4px rgba(102, 126, 234, 0.1);
  transform: translateY(-2px);
}

.form-input::placeholder {
  color: #9ca3af;
}

.input-hint {
  margin-top: 0.5rem;
  font-size: 0.8125rem;
  color: #6b7280;
  display: flex;
  align-items: center;
  gap: 0.25rem;
}

.input-hint::before {
  content: "💡";
  font-size: 1rem;
}

.register-button {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.75rem;
  padding: 1.125rem 2rem;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  font-size: 1.125rem;
  font-weight: 700;
  border: none;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
  margin-bottom: 1.5rem;
  margin-top: 0.5rem;
}

.register-button:hover:not(.button-disabled) {
  transform: translateY(-2px);
  box-shadow: 0 6px 25px rgba(102, 126, 234, 0.5);
}

.register-button:active:not(.button-disabled) {
  transform: translateY(0);
}

.button-disabled {
  opacity: 0.5;
  cursor: not-allowed;
  box-shadow: none;
}

.button-text {
  flex: 1;
  text-align: center;
}

.button-icon {
  font-size: 1.5rem;
  font-weight: 700;
  transition: transform 0.3s ease;
}

.register-button:hover:not(.button-disabled) .button-icon {
  transform: translateX(5px);
}

.login-prompt {
  text-align: center;
  padding: 1.5rem;
  background: linear-gradient(135deg, #f0f4ff 0%, #e5edff 100%);
  border-radius: 12px;
  border: 1px solid #e0e7ff;
}

.login-prompt p {
  font-size: 0.9375rem;
  color: #6b7280;
  margin-bottom: 0.5rem;
}

.login-link {
  display: inline-block;
  color: #667eea;
  font-weight: 700;
  font-size: 1rem;
  text-decoration: none;
  transition: all 0.2s ease;
  padding: 0.5rem 1rem;
  border-radius: 8px;
}

.login-link:hover {
  background: white;
  transform: translateY(-2px);
  box-shadow: 0 2px 8px rgba(102, 126, 234, 0.2);
}

/* Responsive Design */
@media (max-width: 640px) {
  .register-container {
    padding: 1rem;
  }

  .register-card {
    border-radius: 20px;
  }

  .card-header {
    padding: 2rem 1.5rem 1.5rem;
    border-radius: 20px 20px 0 0;
  }

  .header-icon {
    font-size: 3rem;
  }

  .card-title {
    font-size: 1.75rem;
  }

  .card-subtitle {
    font-size: 0.9375rem;
  }

  .register-form {
    padding: 2rem 1.5rem;
  }

  .form-input {
    padding: 0.875rem 1rem;
  }

  .register-button {
    padding: 1rem 1.5rem;
    font-size: 1rem;
  }
}
</style>