<template>
  <BarNav />

  <div class="login-container">
    <div class="login-background">
      <div class="floating-shape shape-1"></div>
      <div class="floating-shape shape-2"></div>
      <div class="floating-shape shape-3"></div>
    </div>

    <div class="login-card">
      <div class="card-header">
        <div class="header-icon">🔐</div>
        <h2 class="card-title">Welcome Back</h2>
        <p class="card-subtitle">Sign in to access the Lost & Found Board</p>
      </div>

      <form @submit.prevent="handleLogin" class="login-form">
        <div class="form-group">
          <label for="email" class="form-label">
            <span class="label-icon">📧</span>
            <span>Email Address</span>
          </label>
          <div class="input-wrapper">
            <input
              v-model="credentials.email"
              id="email"
              type="email"
              placeholder="user@board.com"
              required
              class="form-input"
              :class="{ 'input-error': error }"
            />
          </div>
        </div>

        <div class="form-group">
          <label for="password" class="form-label">
            <span class="label-icon">🔑</span>
            <span>Password</span>
          </label>
          <div class="input-wrapper">
            <input
              v-model="credentials.password"
              id="password"
              type="password"
              placeholder="Enter your password"
              required
              class="form-input"
              :class="{ 'input-error': error }"
            />
          </div>
        </div>

        <transition name="error-fade">
          <div v-if="error" class="error-message">
            <span class="error-icon">⚠️</span>
            <span>{{ error }}</span>
          </div>
        </transition>

        <button type="submit" class="login-button">
          <span class="button-text">Sign In</span>
          <span class="button-icon">→</span>
        </button>

      </form>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "../store/userstore";
import BarNav from "../components/BarNav.vue";

const userStore = useUserStore();
const router = useRouter();

const credentials = ref({ email: "", password: "" });
const error = ref(null);

onMounted(() => {
  if (userStore.user) {
    if (userStore.isAdmin) {
      router.push({ name: "AdminDashboard" });
    } else {
      router.push({ name: "Home" });
    }
  }
});

const handleLogin = async () => {
  error.value = null;

  const success = await userStore.login(credentials.value);
  if (success) {
    if (userStore.isAdmin) {
      router.push({ name: "AdminDashboard" });
    } else {

      router.push({ name: "Home" });
    }
  } else {
    error.value = "Invalid email or password. Please try again.";
  }
};
</script>

<style scoped>
.login-container {
  min-height: calc(100vh - 80px);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2rem 1rem;
  position: relative;
  overflow: hidden;
}

.login-background {
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
  right: -100px;
  animation-delay: 0s;
}

.shape-2 {
  width: 200px;
  height: 200px;
  background: white;
  bottom: -50px;
  left: -50px;
  animation-delay: 5s;
}

.shape-3 {
  width: 150px;
  height: 150px;
  background: white;
  top: 50%;
  left: 10%;
  animation-delay: 10s;
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

.login-card {
  width: 100%;
  max-width: 480px;
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
  animation: bounce 2s infinite;
}

@keyframes bounce {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-10px);
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

.login-form {
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

.input-wrapper {
  position: relative;
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

.input-error {
  border-color: #ef4444;
  background: #fef2f2;
}

.input-error:focus {
  border-color: #ef4444;
  box-shadow: 0 0 0 4px rgba(239, 68, 68, 0.1);
}

.error-message {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 1rem;
  background: #fef2f2;
  border: 1px solid #fecaca;
  border-radius: 10px;
  color: #dc2626;
  font-size: 0.9rem;
  font-weight: 500;
  margin-bottom: 1rem;
}

.error-icon {
  font-size: 1.25rem;
}

.error-fade-enter-active,
.error-fade-leave-active {
  transition: all 0.3s ease;
}

.error-fade-enter-from {
  opacity: 0;
  transform: translateY(-10px);
}

.error-fade-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

.login-button {
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
  margin-bottom: 2rem;
}

.login-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 25px rgba(102, 126, 234, 0.5);
}

.login-button:active {
  transform: translateY(0);
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

.login-button:hover .button-icon {
  transform: translateX(5px);
}

.demo-credentials {
  padding: 1.5rem;
  background: linear-gradient(135deg, #f0f4ff 0%, #e5edff 100%);
  border-radius: 12px;
  border: 1px solid #e0e7ff;
}

.demo-title {
  font-size: 0.875rem;
  font-weight: 700;
  color: #667eea;
  margin-bottom: 1rem;
  text-align: center;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.demo-accounts {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.demo-account {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1rem;
  background: white;
  border-radius: 8px;
  font-size: 0.875rem;
}

.demo-label {
  font-weight: 600;
  color: #6b7280;
  min-width: 60px;
}

.demo-value {
  color: #374151;
  font-family: 'Courier New', monospace;
  background: #f3f4f6;
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  flex: 1;
}

/* Responsive Design */
@media (max-width: 640px) {
  .login-container {
    padding: 1rem;
  }

  .login-card {
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

  .login-form {
    padding: 2rem 1.5rem;
  }

  .form-input {
    padding: 0.875rem 1rem;
  }

  .login-button {
    padding: 1rem 1.5rem;
    font-size: 1rem;
  }
}
</style>