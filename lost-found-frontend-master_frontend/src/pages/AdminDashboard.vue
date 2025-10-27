<template>
  <div class="admin-container">
    <!-- Animated background -->
    <div class="admin-background">
      <div class="gradient-orb orb-1"></div>
      <div class="gradient-orb orb-2"></div>
      <div class="gradient-orb orb-3"></div>
      <div class="grid-pattern"></div>
    </div>

    <div class="admin-content">
      <!-- Header Section -->
      <div class="admin-header">
        <div class="header-top">
          <div class="header-left">
            <div class="admin-badge">
              <span class="badge-icon">👑</span>
              <span class="badge-text">Admin</span>
            </div>
            <div class="header-text">
              <h1 class="header-title">Admin Dashboard</h1>
              <p class="header-subtitle">Manage and oversee all lost & found items</p>
            </div>
          </div>
          
          <button @click="logout" class="logout-button">
            <span class="logout-icon">🚪</span>
            <span class="logout-text">Logout</span>
          </button>
        </div>

        <!-- Stats Cards -->
        <div class="stats-grid">
          <div class="stat-card stat-card-1">
            <div class="stat-icon-wrapper">
              <div class="stat-icon">📊</div>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ totalItems }}</div>
              <div class="stat-label">Total Items</div>
            </div>
            <div class="stat-glow"></div>
          </div>

          <div class="stat-card stat-card-2">
            <div class="stat-icon-wrapper">
              <div class="stat-icon">😢</div>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ lostItems }}</div>
              <div class="stat-label">Lost Items</div>
            </div>
            <div class="stat-glow"></div>
          </div>

          <div class="stat-card stat-card-3">
            <div class="stat-icon-wrapper">
              <div class="stat-icon">✨</div>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ foundItems }}</div>
              <div class="stat-label">Found Items</div>
            </div>
            <div class="stat-glow"></div>
          </div>

          <div class="stat-card stat-card-4">
            <div class="stat-icon-wrapper">
              <div class="stat-icon">👥</div>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ activeUsers }}</div>
              <div class="stat-label">Active Users</div>
            </div>
            <div class="stat-glow"></div>
          </div>
        </div>
      </div>

      <!-- Management Section -->
      <div class="management-section">
        <div class="section-header">
          <div class="section-title-wrapper">
            <div class="section-icon">🛠️</div>
            <h2 class="section-title">Item Management</h2>
          </div>
          <div class="section-actions">
            <button class="action-button action-refresh" @click="refreshData">
              <span class="action-icon">🔄</span>
              <span>Refresh</span>
            </button>
          </div>
        </div>

        <!-- Item List -->
        <div class="item-list-container">
          <ItemList :isAdmin="userStore.isAdmin" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '../store/userstore';
import { useItemStore } from '../store/itemstore';
import ItemList from '../components/ItemList.vue';

const userStore = useUserStore();
const itemStore = useItemStore();
const router = useRouter();

const totalItems = computed(() => itemStore.items?.length || 0);
const lostItems = computed(() => itemStore.items?.filter(item => item.status === 'lost').length || 0);
const foundItems = computed(() => itemStore.items?.filter(item => item.status === 'found').length || 0);
const activeUsers = ref(42); // You can replace this with actual data from your API

// Redirect non-admins
onMounted(() => {
  userStore.loadFromStorage();
  if (!userStore.isAuthenticated || !userStore.isAdmin) {
    router.push({ name: 'Login' });
  }
  itemStore.fetchItems();
});

// Logout function
const logout = () => {
  userStore.logout();
  router.push({ name: 'Login' });
};

// Refresh data
const refreshData = async () => {
  await itemStore.fetchItems();
};
</script>

<style scoped>
.admin-container {
  min-height: 100vh;
  position: relative;
  overflow-x: hidden;
}

/* Background */
.admin-background {
  position: fixed;
  inset: 0;
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%);
  z-index: 0;
}

.gradient-orb {
  position: absolute;
  border-radius: 50%;
  filter: blur(80px);
  opacity: 0.2;
  animation: orbFloat 20s ease-in-out infinite;
}

.orb-1 {
  width: 500px;
  height: 500px;
  background: radial-gradient(circle, rgba(102, 126, 234, 0.8) 0%, transparent 70%);
  top: -150px;
  left: -150px;
  animation-delay: 0s;
}

.orb-2 {
  width: 400px;
  height: 400px;
  background: radial-gradient(circle, rgba(236, 72, 153, 0.6) 0%, transparent 70%);
  bottom: -100px;
  right: -100px;
  animation-delay: 7s;
}

.orb-3 {
  width: 350px;
  height: 350px;
  background: radial-gradient(circle, rgba(168, 85, 247, 0.5) 0%, transparent 70%);
  top: 50%;
  left: 50%;
  animation-delay: 14s;
}

@keyframes orbFloat {
  0%, 100% { transform: translate(0, 0); }
  33% { transform: translate(60px, -60px); }
  66% { transform: translate(-40px, 40px); }
}

.grid-pattern {
  position: absolute;
  inset: 0;
  background-image: 
    linear-gradient(rgba(102, 126, 234, 0.03) 1px, transparent 1px),
    linear-gradient(90deg, rgba(102, 126, 234, 0.03) 1px, transparent 1px);
  background-size: 50px 50px;
}

/* Content */
.admin-content {
  position: relative;
  z-index: 1;
  padding: 2rem;
  max-width: 1400px;
  margin: 0 auto;
}

/* Header */
.admin-header {
  margin-bottom: 3rem;
  animation: fadeInDown 0.8s ease-out;
}

.header-top {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 3rem;
  gap: 2rem;
  flex-wrap: wrap;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 1.5rem;
  flex: 1;
}

.admin-badge {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  padding: 1rem 1.5rem;
  background: rgba(255, 215, 0, 0.15);
  border: 2px solid rgba(255, 215, 0, 0.3);
  border-radius: 20px;
  animation: badgePulse 3s ease-in-out infinite;
}

@keyframes badgePulse {
  0%, 100% { box-shadow: 0 0 0 0 rgba(255, 215, 0, 0.4); }
  50% { box-shadow: 0 0 0 10px rgba(255, 215, 0, 0); }
}

.badge-icon {
  font-size: 2rem;
  animation: crownBounce 2s ease-in-out infinite;
}

@keyframes crownBounce {
  0%, 100% { transform: translateY(0) rotate(0deg); }
  50% { transform: translateY(-5px) rotate(5deg); }
}

.badge-text {
  font-size: 1.125rem;
  font-weight: 700;
  color: #ffd700;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.header-text {
  flex: 1;
}

.header-title {
  font-size: clamp(2rem, 5vw, 3.5rem);
  font-weight: 900;
  background: linear-gradient(135deg, #ffffff 0%, #e0e7ff 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin-bottom: 0.5rem;
  letter-spacing: -1px;
}

.header-subtitle {
  font-size: 1.125rem;
  color: rgba(255, 255, 255, 0.7);
  font-weight: 300;
}

.logout-button {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  padding: 1rem 2rem;
  background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
  color: white;
  font-size: 1rem;
  font-weight: 700;
  border: none;
  border-radius: 16px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 20px rgba(239, 68, 68, 0.4);
}

.logout-button:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 30px rgba(239, 68, 68, 0.5);
}

.logout-icon {
  font-size: 1.25rem;
}

/* Stats Grid */
.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 1.5rem;
}

.stat-card {
  position: relative;
  display: flex;
  align-items: center;
  gap: 1.5rem;
  padding: 2rem;
  background: rgba(255, 255, 255, 0.05);
  backdrop-filter: blur(20px);
  border: 1px solid rgba(255, 255, 255, 0.1);
  border-radius: 24px;
  overflow: hidden;
  transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  animation: fadeInUp 0.8s ease-out backwards;
}

.stat-card-1 { animation-delay: 0.1s; }
.stat-card-2 { animation-delay: 0.2s; }
.stat-card-3 { animation-delay: 0.3s; }
.stat-card-4 { animation-delay: 0.4s; }

.stat-card:hover {
  transform: translateY(-8px) scale(1.02);
  border-color: rgba(255, 255, 255, 0.2);
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.3);
}

.stat-icon-wrapper {
  width: 70px;
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 20px;
  flex-shrink: 0;
  transition: all 0.3s ease;
}

.stat-card:hover .stat-icon-wrapper {
  transform: scale(1.1) rotate(5deg);
}

.stat-icon {
  font-size: 2.5rem;
}

.stat-content {
  flex: 1;
}

.stat-value {
  font-size: 2.5rem;
  font-weight: 900;
  color: white;
  line-height: 1;
  margin-bottom: 0.5rem;
}

.stat-label {
  font-size: 0.875rem;
  color: rgba(255, 255, 255, 0.6);
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.stat-glow {
  position: absolute;
  inset: 0;
  background: radial-gradient(circle at center, rgba(102, 126, 234, 0.2) 0%, transparent 70%);
  opacity: 0;
  transition: opacity 0.4s ease;
}

.stat-card:hover .stat-glow {
  opacity: 1;
}

/* Management Section */
.management-section {
  background: rgba(255, 255, 255, 0.03);
  backdrop-filter: blur(20px);
  border: 1px solid rgba(255, 255, 255, 0.08);
  border-radius: 30px;
  padding: 2.5rem;
  animation: fadeInUp 1s ease-out backwards;
  animation-delay: 0.5s;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
  flex-wrap: wrap;
  gap: 1.5rem;
}

.section-title-wrapper {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.section-icon {
  font-size: 2rem;
  animation: bounce 2s infinite;
}

.section-title {
  font-size: 2rem;
  font-weight: 800;
  color: white;
  letter-spacing: -0.5px;
}

.section-actions {
  display: flex;
  gap: 1rem;
}

.action-button {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.875rem 1.5rem;
  background: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
  color: white;
  font-size: 0.9375rem;
  font-weight: 600;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.action-button:hover {
  background: rgba(255, 255, 255, 0.15);
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
}

.action-icon {
  font-size: 1.125rem;
}

.action-refresh:active .action-icon {
  animation: spin 0.6s ease-in-out;
}

/* Item List Container */
.item-list-container {
  background: rgba(0, 0, 0, 0.2);
  border-radius: 20px;
  padding: 1.5rem;
  min-height: 400px;
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

@keyframes bounce {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-5px); }
}

/* Responsive */
@media (max-width: 768px) {
  .admin-content {
    padding: 1.5rem 1rem;
  }

  .header-top {
    flex-direction: column;
  }

  .header-left {
    flex-direction: column;
    align-items: flex-start;
  }

  .admin-badge {
    padding: 0.75rem 1.25rem;
  }

  .badge-icon {
    font-size: 1.5rem;
  }

  .badge-text {
    font-size: 1rem;
  }

  .logout-button {
    width: 100%;
    justify-content: center;
  }

  .stats-grid {
    grid-template-columns: 1fr;
  }

  .management-section {
    padding: 1.5rem;
  }

  .section-header {
    flex-direction: column;
    align-items: flex-start;
  }

  .section-title {
    font-size: 1.5rem;
  }

  .item-list-container {
    padding: 1rem;
  }
}
</style>