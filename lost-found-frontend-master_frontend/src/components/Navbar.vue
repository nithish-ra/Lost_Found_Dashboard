<template>
  <nav class="navbar">
    <div class="nav-container">
      <div class="nav-links">
        <router-link to="/home" class="nav-link">
          <span class="icon">🏠</span>
          <span>Home</span>
        </router-link>
        <router-link to="/items" class="nav-link">
          <span class="icon">🔍</span>
          <span>Lost & Found</span>
        </router-link>
        <router-link to="/id" class="nav-link">
          <span class="icon">👤</span>
          <span>User</span>
        </router-link>
      </div>
      <router-link to="/" class="nav-link logout" @click="logout">
        <span class="icon">🚪</span>
        <span>Logout</span>
      </router-link>
    </div>
  </nav>
</template>

<script setup>
import { useRouter } from "vue-router";
import { useUserStore } from "../store/userstore";
const router = useRouter();
const userStore = useUserStore();

const logout = () => {
  userStore.logout();
  router.push({ name: "Login" });
};
</script>

<style scoped>
.navbar {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 0;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 100;
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 2rem;
}

.nav-links {
  display: flex;
  gap: 0.5rem;
  flex: 1;
}

.nav-link {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1.25rem;
  color: white;
  text-decoration: none;
  border-radius: 8px;
  font-weight: 500;
  transition: all 0.3s ease;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
}

.nav-link:hover {
  background: rgba(255, 255, 255, 0.2);
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.nav-link.router-link-active {
  background: rgba(255, 255, 255, 0.25);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.icon {
  font-size: 1.2rem;
  display: inline-flex;
  align-items: center;
}

.logout {
  background: rgba(239, 68, 68, 0.2);
  margin-left: auto;
}

.logout:hover {
  background: rgba(239, 68, 68, 0.3);
}

/* Responsive design */
@media (max-width: 768px) {
  .nav-container {
    flex-direction: column;
    padding: 1rem;
    gap: 1rem;
  }
  
  .nav-links {
    flex-direction: column;
    width: 100%;
  }
  
  .nav-link {
    width: 100%;
    justify-content: center;
  }
  
  .logout {
    width: 100%;
    justify-content: center;
  }
}
</style>