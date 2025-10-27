<template>
  <Navbar />
  <div class="profile-container">
    <!-- Animated background elements -->
    <div class="bg-shapes">
      <div class="shape shape-1"></div>
      <div class="shape shape-2"></div>
      <div class="shape shape-3"></div>
    </div>
    
    <div class="content-wrapper">
      <h2 class="page-title">
        <span class="title-text">User Profile</span>
        <div class="title-underline"></div>
      </h2>
      
      <div class="profile-card">
        <!-- Decorative corner accents -->
        <div class="corner-accent top-left"></div>
        <div class="corner-accent top-right"></div>
        <div class="corner-accent bottom-left"></div>
        <div class="corner-accent bottom-right"></div>
        
        <div class="card-content">
          <!-- Avatar with animated ring -->
          <div class="avatar-container">
            <div class="avatar-ring"></div>
            <div class="avatar">
              <span class="avatar-letter">
                {{ userStore.user ? userStore.user.name.charAt(0).toUpperCase() : '?' }}
              </span>
            </div>
          </div>
          
          <!-- User information -->
          <div v-if="userStore.user" class="user-info">
            <div class="info-item" v-for="(value, key) in userInfo" :key="key">
              <div class="info-icon">
                <div class="icon-circle">
                  <span v-html="getIcon(key)"></span>
                </div>
              </div>
              <div class="info-content">
                <span class="info-label">{{ key }}</span>
                <span class="info-value">{{ value }}</span>
              </div>
            </div>
          </div>
          
          <!-- No data state -->
          <div v-else class="no-data">
            <div class="no-data-icon">⚠️</div>
            <p class="no-data-text">No user data found</p>
            <p class="no-data-subtext">Please check your connection and try again</p>
          </div>
        </div>
      </div>

      <!-- User's Posted Items Section -->
      <div v-if="userStore.user" class="items-section">
        <h3 class="section-title">
          <span class="section-icon">📋</span>
          <span>My Posted Items</span>
          <span class="items-count">{{ userItems.length }}</span>
        </h3>

        <!-- Loading state -->
        <div v-if="loadingItems" class="loading-container">
          <div class="loading-spinner"></div>
          <p class="loading-text">Loading your items...</p>
        </div>

        <!-- Empty state -->
        <div v-else-if="userItems.length === 0" class="empty-items">
          <div class="empty-icon">📦</div>
          <p class="empty-text">No items posted yet</p>
          <p class="empty-subtext">Start by posting a lost or found item</p>
        </div>

        <!-- Items grid -->
        <div v-else class="items-grid">
          <div 
            v-for="item in userItems" 
            :key="item.id" 
            class="user-item-card"
            :class="{ 
              'item-lost': item.status === 'lost',
              'item-found': item.status === 'found'
            }"
          >
            <!-- Status badge -->
            <div class="item-status-badge" :class="item.status">
              <span class="status-icon">{{ item.status === 'lost' ? '😢' : '✨' }}</span>
              <span class="status-text">{{ item.status }}</span>
            </div>

            <!-- Item content -->
            <div class="item-header">
              <h4 class="item-title">{{ item.title }}</h4>
            </div>

            <p class="item-description">{{ item.description }}</p>

            <div class="item-details">
              <div class="item-detail">
                <span class="detail-icon">📍</span>
                <span class="detail-text">{{ item.location }}</span>
              </div>
              <div class="item-detail">
                <span class="detail-icon">📞</span>
                <span class="detail-text">{{ item.contact }}</span>
              </div>
            </div>

            <!-- Toggle status button -->
            <button 
              @click="toggleItemStatus(item)" 
              class="toggle-button"
              :disabled="togglingItem === item.id"
            >
              <span class="toggle-icon">🔄</span>
              <span class="toggle-text">
                {{ togglingItem === item.id ? 'Updating...' : `Mark as ${item.status === 'lost' ? 'Found' : 'Lost'}` }}
              </span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import Navbar from "../components/Navbar.vue";
import { useUserStore } from "../store/userstore.js";
import { computed, ref, onMounted } from "vue";

const userStore = useUserStore();
const userItems = ref([]);
const loadingItems = ref(false);
const togglingItem = ref(null);

const userInfo = computed(() => {
  if (!userStore.user) return {};
  return {
    id: userStore.user.id,
    Name: userStore.user.name,
    Email: userStore.user.email,
    Contact: userStore.user.phone
  };
});

const getIcon = (key) => {
  const icons = {
    Name: '👤',
    Email: '✉️',
    Contact: '📱'
  };
  return icons[key] || '•';
};

// Fetch user's items
const fetchUserItems = async () => {
  if (!userStore.user?.id) return;
  
  loadingItems.value = true;
  try {
    const response = await fetch(`http://localhost:8080/api/items/users/${userStore.user.id}`);
    if (response.ok) {
      userItems.value = await response.json();
    } else {
      console.error('Failed to fetch user items');
      userItems.value = [];
    }
  } catch (error) {
    console.error('Error fetching user items:', error);
    userItems.value = [];
  } finally {
    loadingItems.value = false;
  }
};

// Toggle item status
const toggleItemStatus = async (item) => {
  togglingItem.value = item.id;
  
  try {
    const newStatus = item.status === 'lost' ? 'found' : 'lost';
    const response = await fetch(`http://localhost:8080/api/items/${item.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        ...item,
        status: newStatus
      })
    });

    if (response.ok) {
      // Update local item status
      item.status = newStatus;
      // Show success message
      alert(`Item status updated to "${newStatus}"!`);
    } else {
      alert('Failed to update item status');
    }
  } catch (error) {
    console.error('Error toggling item status:', error);
    alert('Error updating item status');
  } finally {
    togglingItem.value = null;
  }
};

onMounted(() => {
  fetchUserItems();
});
</script>

<style scoped>
.profile-container {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 50%, #f093fb 100%);
  padding: 60px 20px;
  position: relative;
  overflow: hidden;
}

/* Animated background shapes */
.bg-shapes {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  overflow: hidden;
  z-index: 0;
}

.shape {
  position: absolute;
  border-radius: 50%;
  filter: blur(60px);
  opacity: 0.3;
  animation: float 20s infinite ease-in-out;
}

.shape-1 {
  width: 400px;
  height: 400px;
  background: linear-gradient(45deg, #ff6b9d, #c06c84);
  top: -100px;
  left: -100px;
  animation-delay: 0s;
}

.shape-2 {
  width: 500px;
  height: 500px;
  background: linear-gradient(45deg, #4facfe, #00f2fe);
  bottom: -150px;
  right: -150px;
  animation-delay: 5s;
}

.shape-3 {
  width: 300px;
  height: 300px;
  background: linear-gradient(45deg, #43e97b, #38f9d7);
  top: 50%;
  right: 10%;
  animation-delay: 10s;
}

@keyframes float {
  0%, 100% { transform: translate(0, 0) scale(1); }
  33% { transform: translate(50px, -50px) scale(1.1); }
  66% { transform: translate(-30px, 30px) scale(0.9); }
}

.content-wrapper {
  position: relative;
  z-index: 1;
  max-width: 1200px;
  margin: 0 auto;
}

/* Page title */
.page-title {
  text-align: center;
  margin-bottom: 50px;
  animation: fadeInDown 0.8s ease-out;
}

.title-text {
  display: block;
  font-size: 3rem;
  font-weight: 900;
  background: linear-gradient(135deg, #ffffff, #e0e7ff);
  -webkit-background-clip: text;
  background-clip: text;
  -webkit-text-fill-color: transparent;
  text-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  letter-spacing: -1px;
}

.title-underline {
  width: 80px;
  height: 4px;
  background: linear-gradient(90deg, transparent, #fff, transparent);
  margin: 15px auto 0;
  border-radius: 2px;
  animation: shimmer 2s infinite;
}

@keyframes shimmer {
  0%, 100% { opacity: 0.5; transform: scaleX(0.8); }
  50% { opacity: 1; transform: scaleX(1); }
}

/* Profile card */
.profile-card {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(20px);
  border-radius: 30px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3),
              0 0 0 1px rgba(255, 255, 255, 0.1) inset;
  padding: 50px 40px;
  position: relative;
  transition: all 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  animation: fadeInUp 1s ease-out;
  max-width: 500px;
  margin: 0 auto 60px;
}

.profile-card:hover {
  transform: translateY(-10px) scale(1.02);
  box-shadow: 0 30px 80px rgba(0, 0, 0, 0.4);
}

/* Corner accents */
.corner-accent {
  position: absolute;
  width: 30px;
  height: 30px;
  border: 3px solid;
  border-image: linear-gradient(135deg, #667eea, #764ba2) 1;
}

.corner-accent.top-left {
  top: 15px;
  left: 15px;
  border-right: none;
  border-bottom: none;
  border-top-left-radius: 5px;
}

.corner-accent.top-right {
  top: 15px;
  right: 15px;
  border-left: none;
  border-bottom: none;
  border-top-right-radius: 5px;
}

.corner-accent.bottom-left {
  bottom: 15px;
  left: 15px;
  border-right: none;
  border-top: none;
  border-bottom-left-radius: 5px;
}

.corner-accent.bottom-right {
  bottom: 15px;
  right: 15px;
  border-left: none;
  border-top: none;
  border-bottom-right-radius: 5px;
}

.card-content {
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* Avatar */
.avatar-container {
  position: relative;
  margin-bottom: 40px;
}

.avatar-ring {
  position: absolute;
  top: -10px;
  left: -10px;
  right: -10px;
  bottom: -10px;
  border-radius: 50%;
  background: linear-gradient(135deg, #667eea, #764ba2, #f093fb);
  animation: rotate 3s linear infinite;
  z-index: -1;
}

@keyframes rotate {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

.avatar {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 10px 30px rgba(102, 126, 234, 0.4);
  position: relative;
  animation: pulse 2s infinite;
}

@keyframes pulse {
  0%, 100% { transform: scale(1); }
  50% { transform: scale(1.05); }
}

.avatar-letter {
  font-size: 3.5rem;
  font-weight: 900;
  color: white;
  text-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}

/* User info */
.user-info {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.info-item {
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 20px;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  border-radius: 15px;
  transition: all 0.3s ease;
  animation: slideIn 0.6s ease-out backwards;
}

.info-item:nth-child(1) { animation-delay: 0.1s; }
.info-item:nth-child(2) { animation-delay: 0.2s; }
.info-item:nth-child(3) { animation-delay: 0.3s; }

@keyframes slideIn {
  from {
    opacity: 0;
    transform: translateX(-30px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

.info-item:hover {
  transform: translateX(5px);
  background: linear-gradient(135deg, #e0e7ff 0%, #d0d9f5 100%);
  box-shadow: 0 5px 15px rgba(102, 126, 234, 0.2);
}

.info-icon {
  flex-shrink: 0;
}

.icon-circle {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background: linear-gradient(135deg, #667eea, #764ba2);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
}

.info-content {
  display: flex;
  flex-direction: column;
  gap: 5px;
  flex: 1;
}

.info-label {
  font-size: 0.85rem;
  font-weight: 600;
  color: #667eea;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.info-value {
  font-size: 1.1rem;
  font-weight: 600;
  color: #2d3748;
}

/* No data state */
.no-data {
  text-align: center;
  padding: 40px 20px;
  animation: fadeIn 0.6s ease-out;
}

.no-data-icon {
  font-size: 4rem;
  margin-bottom: 20px;
  animation: bounce 2s infinite;
}

@keyframes bounce {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-10px); }
}

.no-data-text {
  font-size: 1.3rem;
  font-weight: 700;
  color: #4a5568;
  margin-bottom: 10px;
}

.no-data-subtext {
  font-size: 0.95rem;
  color: #718096;
}

/* Items Section */
.items-section {
  margin-top: 60px;
  animation: fadeInUp 1.2s ease-out;
}

.section-title {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 1rem;
  font-size: 2rem;
  font-weight: 800;
  color: white;
  margin-bottom: 40px;
  text-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}

.section-icon {
  font-size: 2.5rem;
  animation: bounce 2s infinite;
}

.items-count {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 40px;
  height: 40px;
  padding: 0 12px;
  background: rgba(255, 255, 255, 0.2);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  font-size: 1.2rem;
  border: 2px solid rgba(255, 255, 255, 0.3);
}

/* Loading state */
.loading-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 60px 20px;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 20px;
}

.loading-spinner {
  width: 60px;
  height: 60px;
  border: 4px solid rgba(255, 255, 255, 0.3);
  border-top: 4px solid white;
  border-radius: 50%;
  animation: spin 1s linear infinite;
  margin-bottom: 20px;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

.loading-text {
  color: white;
  font-size: 1.2rem;
  font-weight: 600;
}

/* Empty items state */
.empty-items {
  text-align: center;
  padding: 60px 20px;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 20px;
}

.empty-icon {
  font-size: 5rem;
  margin-bottom: 20px;
  animation: float 3s ease-in-out infinite;
}

.empty-text {
  font-size: 1.5rem;
  font-weight: 700;
  color: white;
  margin-bottom: 10px;
}

.empty-subtext {
  font-size: 1rem;
  color: rgba(255, 255, 255, 0.8);
}

/* Items grid */
.items-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 25px;
}

.user-item-card {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(20px);
  border-radius: 20px;
  padding: 25px;
  position: relative;
  overflow: hidden;
  transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  animation: fadeInUp 0.6s ease-out backwards;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
}

.user-item-card:nth-child(1) { animation-delay: 0.1s; }
.user-item-card:nth-child(2) { animation-delay: 0.2s; }
.user-item-card:nth-child(3) { animation-delay: 0.3s; }
.user-item-card:nth-child(n+4) { animation-delay: 0.4s; }

.user-item-card::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 5px;
  height: 100%;
  transition: all 0.3s ease;
}

.item-lost::before {
  background: linear-gradient(180deg, #ef4444, #dc2626);
}

.item-found::before {
  background: linear-gradient(180deg, #10b981, #059669);
}

.user-item-card:hover {
  transform: translateY(-8px) scale(1.02);
  box-shadow: 0 20px 50px rgba(0, 0, 0, 0.3);
}

/* Status badge */
.item-status-badge {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.5rem 1rem;
  border-radius: 50px;
  font-size: 0.85rem;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  margin-bottom: 20px;
}

.item-status-badge.lost {
  background: linear-gradient(135deg, #fee2e2, #fecaca);
  color: #991b1b;
  box-shadow: 0 2px 10px rgba(239, 68, 68, 0.3);
}

.item-status-badge.found {
  background: linear-gradient(135deg, #d1fae5, #a7f3d0);
  color: #065f46;
  box-shadow: 0 2px 10px rgba(16, 185, 129, 0.3);
}

.status-icon {
  font-size: 1.2rem;
}

/* Item content */
.item-header {
  margin-bottom: 15px;
}

.item-title {
  font-size: 1.4rem;
  font-weight: 800;
  color: #1f2937;
  line-height: 1.3;
}

.item-description {
  font-size: 0.95rem;
  color: #4b5563;
  line-height: 1.6;
  margin-bottom: 20px;
  padding: 15px;
  background: #f9fafb;
  border-radius: 10px;
  border-left: 3px solid #e5e7eb;
  font-style: italic;
}

.item-details {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 20px;
}

.item-detail {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px;
  background: #f9fafb;
  border-radius: 8px;
  font-size: 0.9rem;
}

.detail-icon {
  font-size: 1.2rem;
  flex-shrink: 0;
}

.detail-text {
  color: #374151;
  font-weight: 500;
}

/* Toggle button */
.toggle-button {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.75rem;
  padding: 14px 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  font-size: 1rem;
  font-weight: 700;
  border: none;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
}

.toggle-button:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 6px 25px rgba(102, 126, 234, 0.5);
}

.toggle-button:active:not(:disabled) {
  transform: translateY(0);
}

.toggle-button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.toggle-icon {
  font-size: 1.2rem;
  animation: rotate 2s linear infinite;
}

.toggle-button:disabled .toggle-icon {
  animation-play-state: running;
}

.toggle-button:not(:disabled) .toggle-icon {
  animation-play-state: paused;
}

/* Animations */
@keyframes fadeInDown {
  from {
    opacity: 0;
    transform: translateY(-30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

/* Responsive design */
@media (max-width: 768px) {
  .items-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 640px) {
  .profile-container {
    padding: 40px 15px;
  }
  
  .title-text {
    font-size: 2.2rem;
  }
  
  .profile-card {
    padding: 40px 25px;
  }
  
  .avatar {
    width: 100px;
    height: 100px;
  }
  
  .avatar-letter {
    font-size: 2.8rem;
  }
  
  .info-item {
    padding: 15px;
  }

  .section-title {
    font-size: 1.5rem;
    flex-wrap: wrap;
  }

  .user-item-card {
    padding: 20px;
  }
}
</style>