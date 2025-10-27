<template>
  <div class="board-container">
    <div class="board-header">
      <div class="header-content">
        <h2 class="board-title">Lost & Found Board</h2>
        <p class="board-subtitle">Helping our community reconnect with their belongings</p>
      </div>
      <div class="stats-bar">
        <div class="stat-item">
          <span class="stat-number">{{ lostCount }}</span>
          <span class="stat-label">Lost Items</span>
        </div>
        <div class="stat-divider"></div>
        <div class="stat-item">
          <span class="stat-number">{{ foundCount }}</span>
          <span class="stat-label">Found Items</span>
        </div>
        <div class="stat-divider"></div>
        <button @click="refresh" class="stat-item refresh-button">
          <span class="stat-icon">🔄</span> 
            <span class="stat-label">Refresh</span>
        </button>
      </div>
    </div>
    
    <div v-if="loading" class="loading-state">
      <div class="loading-spinner"></div>
      <p class="loading-text">Loading items...</p>
    </div>
    
    <div v-else-if="items.length === 0" class="empty-state">
      <div class="empty-icon">🔍</div>
      <h3 class="empty-title">No Items Yet</h3>
      <p class="empty-description">Be the first to post a lost or found item and help someone today!</p>
    </div>

    <div v-else class="items-grid">
      <div 
        v-for="it in items" 
        :key="it.id" 
        class="item-card"
        :class="{ 
          'item-card-lost': it.status === 'lost', 
          'item-card-found': it.status === 'found' 
        }"
      >
        <div class="card-ribbon" :class="it.status === 'lost' ? 'ribbon-lost' : 'ribbon-found'">
          <span class="ribbon-icon">{{ it.status === 'lost' ? '😢' : '✨' }}</span>
          <span class="ribbon-text">{{ it.status }}</span>
        </div>

        <div class="card-content">
          <h3 class="item-title">{{ it.title }}</h3>
          
          <div class="item-description-wrapper">
            <p class="item-description">{{ it.description }}</p>
          </div>
          
          <div class="item-details">
            <div class="detail-row">
              <div class="detail-icon">📍</div>
              <div class="detail-content">
                <span class="detail-label">Location</span>
                <span class="detail-value">{{ it.location }}</span>
              </div>
            </div>
            
            <div class="detail-row">
              <div class="detail-icon">📞</div>
              <div class="detail-content">
                <span class="detail-label">Contact</span>
                <span class="detail-value">{{ it.contact }}</span>
              </div>
            </div>
          </div>

          <button 
            v-if="userStore.isAdmin"
            @click="remove(it.id)"
            class="resolve-button"
          >
            <span class="button-icon">✓</span>
            <span>Mark as Resolved</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, computed } from "vue";
import { useItemStore } from "../store/itemstore";
import { useUserStore } from "../store/userstore";

const store = useItemStore();
const userStore = useUserStore();

const { items, fetchItems, deleteItem, loading } = store;

const lostCount = computed(() => items.filter(item => item.status === 'lost').length);
const foundCount = computed(() => items.filter(item => item.status === 'found').length);

onMounted(fetchItems);

const remove = async (id) => {
  if (confirm("Are you sure you want to mark this item as resolved?")) {
    await deleteItem(id);
    alert("Item marked as resolved!");
  }
};

const refresh = async () => {
  fetchItems();
};
</script>

<style scoped>
.board-container {
  min-height: 100vh;
  background: linear-gradient(to bottom, #f0f4ff 0%, #e5edff 100%);
  padding: 2rem 1rem;
}

.board-header {
  max-width: 1200px;
  margin: 0 auto 3rem;
  text-align: center;
}

.header-content {
  margin-bottom: 2rem;
}

.board-title {
  font-size: 2.5rem;
  font-weight: 800;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin-bottom: 0.5rem;
}

.board-subtitle {
  font-size: 1.125rem;
  color: #6b7280;
  font-weight: 400;
}

.stats-bar {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 2rem;
  padding: 1.5rem;
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  max-width: 400px;
  margin: 0 auto;
}

.stat-item {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.stat-number {
  font-size: 2rem;
  font-weight: 700;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.stat-label {
  font-size: 0.875rem;
  color: #6b7280;
  margin-top: 0.25rem;
}

.stat-divider {
  width: 1px;
  height: 40px;
  background: #e5e7eb;
}

/* Loading State */
.loading-state {
  max-width: 1200px;
  margin: 4rem auto;
  text-align: center;
}

.loading-spinner {
  width: 60px;
  height: 60px;
  border: 4px solid #e5e7eb;
  border-top: 4px solid #667eea;
  border-radius: 50%;
  margin: 0 auto 1rem;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

.loading-text {
  font-size: 1.25rem;
  color: #667eea;
  font-weight: 600;
}

/* Empty State */
.empty-state {
  max-width: 500px;
  margin: 4rem auto;
  text-align: center;
  padding: 3rem 2rem;
  background: white;
  border-radius: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.empty-icon {
  font-size: 5rem;
  margin-bottom: 1.5rem;
  animation: float 3s ease-in-out infinite;
}

@keyframes float {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-20px); }
}

.empty-title {
  font-size: 1.75rem;
  font-weight: 700;
  color: #1f2937;
  margin-bottom: 0.75rem;
}

.empty-description {
  font-size: 1rem;
  color: #6b7280;
  line-height: 1.6;
}

/* Items Grid */
.items-grid {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 1.5rem;
}

/* Item Cards */
.item-card {
  background: white;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
  position: relative;
}

.item-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 12px 28px rgba(0, 0, 0, 0.15);
}

.item-card-lost {
  border-left: 5px solid #ef4444;
}

.item-card-found {
  border-left: 5px solid #10b981;
}

.card-ribbon {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1.25rem;
  font-size: 0.875rem;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.ribbon-lost {
  background: linear-gradient(135deg, #fee2e2 0%, #fecaca 100%);
  color: #991b1b;
}

.ribbon-found {
  background: linear-gradient(135deg, #d1fae5 0%, #a7f3d0 100%);
  color: #065f46;
}

.ribbon-icon {
  font-size: 1.25rem;
}

.card-content {
  padding: 1.5rem;
}

.item-title {
  font-size: 1.375rem;
  font-weight: 700;
  color: #1f2937;
  margin-bottom: 1rem;
  line-height: 1.3;
}

.item-description-wrapper {
  margin-bottom: 1.5rem;
  padding: 1rem;
  background: #f9fafb;
  border-radius: 12px;
  border-left: 3px solid #e5e7eb;
}

.item-description {
  font-size: 0.9375rem;
  color: #4b5563;
  line-height: 1.6;
  font-style: italic;
}

.item-details {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.detail-row {
  display: flex;
  align-items: flex-start;
  gap: 0.75rem;
  padding: 0.75rem;
  background: #f9fafb;
  border-radius: 10px;
  transition: all 0.2s ease;
}

.detail-row:hover {
  background: #f3f4f6;
}

.detail-icon {
  font-size: 1.5rem;
  flex-shrink: 0;
}

.detail-content {
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  flex: 1;
  min-width: 0;
}

.detail-label {
  font-size: 0.75rem;
  font-weight: 600;
  color: #6b7280;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.detail-value {
  font-size: 0.9375rem;
  color: #1f2937;
  font-weight: 500;
  word-wrap: break-word;
}

.resolve-button {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  padding: 0.875rem;
  background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
  color: white;
  font-size: 0.9375rem;
  font-weight: 600;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 2px 8px rgba(239, 68, 68, 0.3);
}

.resolve-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(239, 68, 68, 0.4);
}

.resolve-button:active {
  transform: translateY(0);
}

.button-icon {
  font-size: 1.125rem;
}

/* Responsive Design */
@media (max-width: 768px) {
  .board-title {
    font-size: 2rem;
  }

  .board-subtitle {
    font-size: 1rem;
  }

  .stats-bar {
    flex-direction: column;
    gap: 1rem;
    padding: 1.25rem;
  }

  .stat-divider {
    width: 100%;
    height: 1px;
  }

  .items-grid {
    grid-template-columns: 1fr;
    padding: 0 0.5rem;
  }

  .empty-icon {
    font-size: 4rem;
  }
}

/* Enhanced Stat Icon Styling */
.stat-icon-wrapper {
  width: 70px;
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 20px;
  flex-shrink: 0;
  position: relative;
  transition: all 0.3s ease;
}

/* Individual stat card icon backgrounds */
.stat-card-1 .stat-icon-wrapper {
  background: linear-gradient(135deg, rgba(59, 130, 246, 0.2) 0%, rgba(37, 99, 235, 0.3) 100%);
  box-shadow: 0 4px 20px rgba(59, 130, 246, 0.3);
}

.stat-card-2 .stat-icon-wrapper {
  background: linear-gradient(135deg, rgba(239, 68, 68, 0.2) 0%, rgba(220, 38, 38, 0.3) 100%);
  box-shadow: 0 4px 20px rgba(239, 68, 68, 0.3);
}

.stat-card-3 .stat-icon-wrapper {
  background: linear-gradient(135deg, rgba(16, 185, 129, 0.2) 0%, rgba(5, 150, 105, 0.3) 100%);
  box-shadow: 0 4px 20px rgba(16, 185, 129, 0.3);
}

.stat-card-4 .stat-icon-wrapper {
  background: linear-gradient(135deg, rgba(168, 85, 247, 0.2) 0%, rgba(126, 34, 206, 0.3) 100%);
  box-shadow: 0 4px 20px rgba(168, 85, 247, 0.3);
}

/* Hover effects for icon wrappers */
.stat-card-1:hover .stat-icon-wrapper {
  background: linear-gradient(135deg, rgba(59, 130, 246, 0.3) 0%, rgba(37, 99, 235, 0.4) 100%);
  box-shadow: 0 8px 30px rgba(59, 130, 246, 0.5);
}

.stat-card-2:hover .stat-icon-wrapper {
  background: linear-gradient(135deg, rgba(239, 68, 68, 0.3) 0%, rgba(220, 38, 38, 0.4) 100%);
  box-shadow: 0 8px 30px rgba(239, 68, 68, 0.5);
}

.stat-card-3:hover .stat-icon-wrapper {
  background: linear-gradient(135deg, rgba(16, 185, 129, 0.3) 0%, rgba(5, 150, 105, 0.4) 100%);
  box-shadow: 0 8px 30px rgba(16, 185, 129, 0.5);
}

.stat-card-4:hover .stat-icon-wrapper {
  background: linear-gradient(135deg, rgba(168, 85, 247, 0.3) 0%, rgba(126, 34, 206, 0.4) 100%);
  box-shadow: 0 8px 30px rgba(168, 85, 247, 0.5);
}

/* Animated glow ring around icon */
.stat-icon-wrapper::before {
  content: '';
  position: absolute;
  inset: -3px;
  border-radius: 22px;
  padding: 2px;
  background: linear-gradient(135deg, transparent, rgba(255, 255, 255, 0.3), transparent);
  -webkit-mask: linear-gradient(#fff 0 0) content-box, linear-gradient(#fff 0 0);
  -webkit-mask-composite: xor;
  mask-composite: exclude;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.stat-card:hover .stat-icon-wrapper::before {
  opacity: 1;
  animation: rotateBorder 3s linear infinite;
}

@keyframes rotateBorder {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

.stat-card:hover .stat-icon-wrapper {
  transform: scale(1.15) rotate(5deg);
}

.stat-icon {
  font-size: 2.5rem;
  filter: drop-shadow(0 4px 10px rgba(0, 0, 0, 0.3));
  transition: all 0.3s ease;
}

.stat-card:hover .stat-icon {
  transform: scale(1.1);
  filter: drop-shadow(0 6px 15px rgba(0, 0, 0, 0.4));
}

/* Pulse animation for specific icons */
.stat-card-1 .stat-icon {
  animation: iconPulse1 3s ease-in-out infinite;
}

.stat-card-2 .stat-icon {
  animation: iconPulse2 3s ease-in-out infinite;
  animation-delay: 0.5s;
}

.stat-card-3 .stat-icon {
  animation: iconPulse3 3s ease-in-out infinite;
  animation-delay: 1s;
}

.stat-card-4 .stat-icon {
  animation: iconPulse4 3s ease-in-out infinite;
  animation-delay: 1.5s;
}

@keyframes iconPulse1 {
  0%, 100% { 
    transform: scale(1);
    filter: drop-shadow(0 4px 10px rgba(59, 130, 246, 0.3));
  }
  50% { 
    transform: scale(1.05);
    filter: drop-shadow(0 6px 20px rgba(59, 130, 246, 0.6));
  }
}

@keyframes iconPulse2 {
  0%, 100% { 
    transform: scale(1);
    filter: drop-shadow(0 4px 10px rgba(239, 68, 68, 0.3));
  }
  50% { 
    transform: scale(1.05);
    filter: drop-shadow(0 6px 20px rgba(239, 68, 68, 0.6));
  }
}

@keyframes iconPulse3 {
  0%, 100% { 
    transform: scale(1);
    filter: drop-shadow(0 4px 10px rgba(16, 185, 129, 0.3));
  }
  50% { 
    transform: scale(1.05);
    filter: drop-shadow(0 6px 20px rgba(16, 185, 129, 0.6));
  }
}

@keyframes iconPulse4 {
  0%, 100% { 
    transform: scale(1);
    filter: drop-shadow(0 4px 10px rgba(168, 85, 247, 0.3));
  }
  50% { 
    transform: scale(1.05);
    filter: drop-shadow(0 6px 20px rgba(168, 85, 247, 0.6));
  }
}

/* Alternative: Add a rotating particle effect behind the icon */
.stat-icon-wrapper::after {
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  border-radius: 20px;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.stat-card-1:hover .stat-icon-wrapper::after {
  background: radial-gradient(circle at 30% 30%, rgba(59, 130, 246, 0.4), transparent 60%);
  opacity: 1;
  animation: particleRotate 4s linear infinite;
}

.stat-card-2:hover .stat-icon-wrapper::after {
  background: radial-gradient(circle at 30% 30%, rgba(239, 68, 68, 0.4), transparent 60%);
  opacity: 1;
  animation: particleRotate 4s linear infinite;
}

.stat-card-3:hover .stat-icon-wrapper::after {
  background: radial-gradient(circle at 30% 30%, rgba(16, 185, 129, 0.4), transparent 60%);
  opacity: 1;
  animation: particleRotate 4s linear infinite;
}

.stat-card-4:hover .stat-icon-wrapper::after {
  background: radial-gradient(circle at 30% 30%, rgba(168, 85, 247, 0.4), transparent 60%);
  opacity: 1;
  animation: particleRotate 4s linear infinite;
}

@keyframes particleRotate {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

/* Colored glow for stat values to match icons */
.stat-card-1 .stat-value {
  color: #60a5fa;
  text-shadow: 0 0 20px rgba(59, 130, 246, 0.5);
}

.stat-card-2 .stat-value {
  color: #f87171;
  text-shadow: 0 0 20px rgba(239, 68, 68, 0.5);
}

.stat-card-3 .stat-value {
  color: #34d399;
  text-shadow: 0 0 20px rgba(16, 185, 129, 0.5);
}

.stat-card-4 .stat-value {
  color: #a78bfa;
  text-shadow: 0 0 20px rgba(168, 85, 247, 0.5);
}

</style>