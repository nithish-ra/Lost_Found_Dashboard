<template>
  <nav class="auth-nav">
    <div class="nav-container">
      <div class="brand-section">
        <div class="logo">
          <span class="logo-icon">🔐</span>
          <span class="logo-text">Lost & Found</span>
        </div>
      </div>
      
      <div class="auth-links">
        <router-link to="/users" class="auth-link register-link">
          <span class="link-icon">✨</span>
          <span>Register</span>
        </router-link>
        <router-link to="/login" class="auth-link login-link">
          <span class="link-icon">🔑</span>
          <span>Login</span>
        </router-link>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.auth-nav {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 1000;
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1.25rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* Brand Section */
.brand-section {
  flex: 1;
}

.logo {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  color: white;
}

.logo-icon {
  font-size: 2rem;
  animation: pulse 2s ease-in-out infinite;
}

@keyframes pulse {
  0%, 100% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.1);
  }
}

.logo-text {
  font-size: 1.5rem;
  font-weight: 800;
  letter-spacing: -0.5px;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

/* Auth Links */
.auth-links {
  display: flex;
  gap: 1rem;
  align-items: center;
}

.auth-link {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1.5rem;
  border-radius: 12px;
  font-weight: 600;
  font-size: 1rem;
  text-decoration: none;
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;
}

.auth-link::before {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: rgba(255, 255, 255, 0.2);
  transition: left 0.3s ease;
}

.auth-link:hover::before {
  left: 100%;
}

.link-icon {
  font-size: 1.25rem;
  display: inline-flex;
  align-items: center;
}

/* Register Link */
.register-link {
  background: rgba(255, 255, 255, 0.15);
  color: white;
  border: 2px solid rgba(255, 255, 255, 0.3);
  backdrop-filter: blur(10px);
}

.register-link:hover {
  background: rgba(255, 255, 255, 0.25);
  border-color: rgba(255, 255, 255, 0.5);
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
}

.register-link.router-link-active {
  background: rgba(255, 255, 255, 0.3);
  border-color: white;
}

/* Login Link */
.login-link {
  background: white;
  color: #667eea;
  border: 2px solid white;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.login-link:hover {
  background: #f8f9ff;
  transform: translateY(-2px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
}

.login-link.router-link-active {
  background: #e8ebff;
  box-shadow: 0 4px 12px rgba(102, 126, 234, 0.3);
}

/* Responsive Design */
@media (max-width: 640px) {
  .nav-container {
    flex-direction: column;
    gap: 1.5rem;
    padding: 1.25rem 1rem;
  }

  .brand-section {
    width: 100%;
    text-align: center;
  }

  .logo {
    justify-content: center;
  }

  .logo-text {
    font-size: 1.25rem;
  }

  .auth-links {
    width: 100%;
    justify-content: center;
    gap: 0.75rem;
  }

  .auth-link {
    flex: 1;
    justify-content: center;
    padding: 0.875rem 1.25rem;
    font-size: 0.9375rem;
  }
}

@media (max-width: 400px) {
  .auth-links {
    flex-direction: column;
    width: 100%;
  }

  .auth-link {
    width: 100%;
  }
}
</style>