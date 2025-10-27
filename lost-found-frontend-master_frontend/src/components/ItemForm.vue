<template>
  <div class="form-container">
    <div class="form-card">
      <div class="form-header">
        <div class="icon-wrapper">
          <span class="header-icon">📝</span>
        </div>
        <h2 class="form-title">Post Lost or Found Item</h2>
        <p class="form-subtitle">Help reunite people with their belongings</p>
      </div>

      <form @submit.prevent="submitForm" class="form-content">
        <div class="form-group">
          <label for="title" class="form-label">
            <span class="label-icon">🏷️</span>
            Item Title
          </label>
          <input 
            v-model="item.title" 
            id="title" 
            placeholder="e.g., Blue Wallet, Black Backpack" 
            required 
            class="form-input" 
          />
        </div>

        <div class="form-group">
          <label for="description" class="form-label">
            <span class="label-icon">📋</span>
            Description
          </label>
          <textarea 
            v-model="item.description" 
            id="description" 
            placeholder="Details like brand, color, contents, distinguishing features..." 
            required 
            rows="4" 
            class="form-input form-textarea"
          ></textarea>
        </div>

        <div class="form-row">
          <div class="form-group">
            <label for="status" class="form-label">
              <span class="label-icon">🔄</span>
              Status
            </label>
            <select v-model="item.status" id="status" required class="form-input form-select">
              <option value="lost">😢 Lost</option>
              <option value="found">✨ Found</option>
            </select>
          </div>

          <div class="form-group">
            <label for="location" class="form-label">
              <span class="label-icon">📍</span>
              Location
            </label>
            <input 
              v-model="item.location" 
              id="location" 
              placeholder="Where was it lost/found?" 
              required 
              class="form-input" 
            />
          </div>
        </div>

        <div class="form-group">
          <label for="contact" class="form-label">
            <span class="label-icon">📞</span>
            Contact Information
          </label>
          <input 
            v-model="item.contact" 
            id="contact" 
            placeholder="Phone number or email address" 
            required 
            class="form-input" 
          />
        </div>

        <button
          type="submit"
          :disabled="!isValid"
          class="submit-button"
          :class="{ 'submit-button-disabled': !isValid }"
        >
          <span class="button-icon">🚀</span>
          <span>Submit Post</span>
        </button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useItemStore } from "../store/itemstore";
import { useUserStore } from "../store/userstore";
const itemStore = useItemStore();

const userStore = useUserStore();
const userId = userStore.user ? userStore.user.id : "";
const item = ref({
  postedByUserId: userId,
  title: "",
  description: "",
  status: "lost",
  location: "",
  contact: "",
});

const isValid = computed(() => 
  item.value.title && item.value.description && item.value.contact && item.value.location
);

const submitForm = async () => {
  await itemStore.addItem(item.value);
  alert("Item posted successfully!");
  // Reset form after submission
  item.value = {
    postedByUserId: userId,
    title: "",
    description: "",
    status: "lost",
    location: "",
    contact: "",
  };
};
</script>

<style scoped>
.form-container {
  min-height: calc(100vh - 80px);
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 2rem 1rem;
  display: flex;
  align-items: center;
  justify-content: center;
}

.form-card {
  width: 100%;
  max-width: 650px;
  background: white;
  border-radius: 24px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
  overflow: hidden;
  animation: slideUp 0.5s ease-out;
}

@keyframes slideUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.form-header {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 2.5rem 2rem;
  text-align: center;
  color: white;
}

.icon-wrapper {
  margin-bottom: 1rem;
}

.header-icon {
  font-size: 3rem;
  display: inline-block;
  animation: bounce 2s infinite;
}

@keyframes bounce {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-10px); }
}

.form-title {
  font-size: 1.875rem;
  font-weight: 700;
  margin-bottom: 0.5rem;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.form-subtitle {
  font-size: 1rem;
  opacity: 0.95;
  font-weight: 300;
}

.form-content {
  padding: 2.5rem 2rem;
}

.form-group {
  margin-bottom: 1.5rem;
  flex: 1;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1.5rem;
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
  width: 95%;
  padding: 0.875rem 1rem;
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

.form-textarea {
  resize: vertical;
  min-height: 100px;
  font-family: inherit;
  line-height: 1.6;
}

.form-select {
  cursor: pointer;
  appearance: none;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 24 24' stroke='%23667eea'%3E%3Cpath stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M19 9l-7 7-7-7'%3E%3C/path%3E%3C/svg%3E");
  background-repeat: no-repeat;
  background-position: right 0.75rem center;
  background-size: 1.5rem;
  padding-right: 2.5rem;
}

.submit-button {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.75rem;
  padding: 1rem 2rem;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  font-size: 1.125rem;
  font-weight: 600;
  border: none;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
  margin-top: 1rem;
}

.submit-button:hover:not(.submit-button-disabled) {
  transform: translateY(-2px);
  box-shadow: 0 6px 25px rgba(102, 126, 234, 0.5);
}

.submit-button:active:not(.submit-button-disabled) {
  transform: translateY(0);
}

.submit-button-disabled {
  opacity: 0.5;
  cursor: not-allowed;
  box-shadow: none;
}

.button-icon {
  font-size: 1.25rem;
}

/* Responsive design */
@media (max-width: 640px) {
  .form-container {
    padding: 1rem;
  }

  .form-card {
    border-radius: 16px;
  }

  .form-header {
    padding: 2rem 1.5rem;
  }

  .form-title {
    font-size: 1.5rem;
  }

  .form-content {
    padding: 1.5rem;
  }

  .form-row {
    grid-template-columns: 1fr;
    gap: 1rem;
  }

  .form-group {
    margin-bottom: 1.25rem;
  }
}
</style>