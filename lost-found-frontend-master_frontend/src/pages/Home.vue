<template>
  <div class="hero-container">
    <!-- Advanced animated background -->
    <div class="background-wrapper">
      <!-- Gradient mesh -->
      <div class="gradient-mesh"></div>
      
      <!-- Animated particles -->
      <div class="particles-container">
        <div class="particle particle-1"></div>
        <div class="particle particle-2"></div>
        <div class="particle particle-3"></div>
        <div class="particle particle-4"></div>
        <div class="particle particle-5"></div>
        <div class="particle particle-6"></div>
        <div class="particle particle-7"></div>
        <div class="particle particle-8"></div>
      </div>
      
      <!-- Animated orbs -->
      <div class="orb orb-1"></div>
      <div class="orb orb-2"></div>
      <div class="orb orb-3"></div>
      
      <!-- Grid overlay -->
      <div class="grid-overlay"></div>
    </div>
    
    <div class="content-wrapper">
      <Navbar />

      <section class="hero-section">
        <div class="hero-card">
          <!-- Premium badge -->
          <div class="premium-badge">
            <span class="badge-icon">✨</span>
            <span class="badge-text">Community Platform</span>
          </div>
          
          <!-- Animated icon -->
          <div class="hero-icon">
            <div class="icon-ring ring-1"></div>
            <div class="icon-ring ring-2"></div>
            <div class="icon-ring ring-3"></div>
            <div class="icon-content">🔑</div>
          </div>
          
          <!-- Main heading -->
          <h1 class="hero-title">
            <span class="title-line">The Lost & Found</span>
            <span class="title-line title-accent">Board</span>
          </h1>
          
          <!-- Subtitle -->
          <p class="hero-subtitle">
            A compassionate community hub where <span class="highlight highlight-pink">lost items find their way home</span> and <span class="highlight highlight-purple">found belongings reunite</span> with their grateful owners.
          </p>
          
          <!-- Feature cards -->
          <div class="feature-grid">
            <div class="feature-card card-browse">
              <div class="feature-background"></div>
              <div class="feature-content">
                <div class="feature-icon-wrapper">
                  <div class="feature-icon">🔍</div>
                  <div class="icon-sparkle sparkle-1"></div>
                  <div class="icon-sparkle sparkle-2"></div>
                  <div class="icon-sparkle sparkle-3"></div>
                </div>
                <h3 class="feature-title"><router-link to="/items" class="nav-link" style="color: white;">Browse Listings</router-link></h3>
                <p class="feature-description">Explore items waiting to be reunited</p>
                <div class="feature-arrow">→</div>
              </div>
              <div class="card-shine"></div>
            </div>
            
            <div class="feature-card card-report">
              <div class="feature-background"></div>
              <div class="feature-content">
                <div class="feature-icon-wrapper">
                  <div class="feature-icon">✍️</div>
                  <div class="icon-sparkle sparkle-1"></div>
                  <div class="icon-sparkle sparkle-2"></div>
                  <div class="icon-sparkle sparkle-3"></div>
                </div>
                <h3 class="feature-title"><router-link to="/items" class="nav-link" style="color: white;">Report an Item</router-link></h3>
                <p class="feature-description">Help someone find what they've lost</p>
                <div class="feature-arrow">→</div>
              </div>
              <div class="card-shine"></div>
            </div>
          </div>
          
          <!-- Stats section -->
          <div class="stats-section">
            <div class="stat-item">
              <div class="stat-number">100+</div>
              <div class="stat-label">Items Reunited</div>
            </div>
            <div class="stat-divider"></div>
            <div class="stat-item">
              <div class="stat-number">500+</div>
              <div class="stat-label">Active Users</div>
            </div>
            <div class="stat-divider"></div>
            <div class="stat-item">
              <div class="stat-number">24/7</div>
              <div class="stat-label">Support Available</div>
            </div>
          </div>
          
          <!-- Decorative dots -->
          <div class="decorative-dots">
            <div class="dot dot-1"></div>
            <div class="dot dot-2"></div>
            <div class="dot dot-3"></div>
          </div>
        </div>
        
        <!-- Scroll indicator -->
        <div class="scroll-indicator">
          <div class="scroll-icon"></div>
          <span class="scroll-text">Scroll to explore</span>
        </div>
      </section>
    </div>
  </div>
</template>

<script setup>
import Navbar from "../components/Navbar.vue";
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

/* Container */
.hero-container {
  min-height: 100vh;
  position: relative;
  overflow: hidden;
  font-family: 'Poppins', sans-serif;
}

/* Background */
.background-wrapper {
  position: absolute;
  inset: 0;
  background: linear-gradient(135deg, #1a0b2e 0%, #2d1b4e 50%, #1a0b2e 100%);
  z-index: 0;
}

.gradient-mesh {
  position: absolute;
  inset: 0;
  background: 
    radial-gradient(ellipse at 20% 30%, rgba(139, 92, 246, 0.15) 0%, transparent 50%),
    radial-gradient(ellipse at 80% 70%, rgba(236, 72, 153, 0.15) 0%, transparent 50%),
    radial-gradient(ellipse at 50% 50%, rgba(168, 85, 247, 0.1) 0%, transparent 70%);
  animation: meshMove 20s ease-in-out infinite;
}

@keyframes meshMove {
  0%, 100% { transform: translate(0, 0) scale(1); }
  50% { transform: translate(30px, -30px) scale(1.1); }
}

/* Particles */
.particles-container {
  position: absolute;
  inset: 0;
  overflow: hidden;
}

.particle {
  position: absolute;
  width: 3px;
  height: 3px;
  background: rgba(168, 85, 247, 0.8);
  border-radius: 50%;
  box-shadow: 0 0 10px rgba(168, 85, 247, 0.5);
  animation: particleFloat 8s ease-in-out infinite;
}

@keyframes particleFloat {
  0% {
    transform: translate(0, 0) scale(0);
    opacity: 0;
  }
  10% {
    opacity: 1;
  }
  90% {
    opacity: 1;
  }
  100% {
    transform: translate(var(--tx), var(--ty)) scale(1.5);
    opacity: 0;
  }
}

.particle-1 { top: 10%; left: 15%; --tx: 100px; --ty: -200px; animation-delay: 0s; }
.particle-2 { top: 70%; left: 85%; --tx: -150px; --ty: -180px; animation-delay: 1.5s; }
.particle-3 { top: 30%; left: 75%; --tx: 120px; --ty: 160px; animation-delay: 3s; }
.particle-4 { top: 85%; left: 25%; --tx: -180px; --ty: -140px; animation-delay: 0.8s; }
.particle-5 { top: 45%; left: 90%; --tx: -120px; --ty: 200px; animation-delay: 2.5s; }
.particle-6 { top: 60%; left: 10%; --tx: 160px; --ty: -160px; animation-delay: 4s; }
.particle-7 { top: 20%; left: 50%; --tx: -140px; --ty: 180px; animation-delay: 1.2s; }
.particle-8 { top: 90%; left: 60%; --tx: 100px; --ty: -220px; animation-delay: 3.5s; }

/* Orbs */
.orb {
  position: absolute;
  border-radius: 50%;
  filter: blur(60px);
  opacity: 0.3;
  animation: orbFloat 15s ease-in-out infinite;
}

.orb-1 {
  width: 400px;
  height: 400px;
  background: radial-gradient(circle, rgba(139, 92, 246, 0.6) 0%, transparent 70%);
  top: -100px;
  right: -100px;
  animation-delay: 0s;
}

.orb-2 {
  width: 350px;
  height: 350px;
  background: radial-gradient(circle, rgba(236, 72, 153, 0.5) 0%, transparent 70%);
  bottom: -80px;
  left: -80px;
  animation-delay: 5s;
}

.orb-3 {
  width: 300px;
  height: 300px;
  background: radial-gradient(circle, rgba(168, 85, 247, 0.4) 0%, transparent 70%);
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  animation-delay: 10s;
}

@keyframes orbFloat {
  0%, 100% { transform: translate(0, 0); }
  33% { transform: translate(50px, -50px); }
  66% { transform: translate(-30px, 30px); }
}

/* Grid overlay */
.grid-overlay {
  position: absolute;
  inset: 0;
  background-image: 
    linear-gradient(rgba(168, 85, 247, 0.05) 1px, transparent 1px),
    linear-gradient(90deg, rgba(168, 85, 247, 0.05) 1px, transparent 1px);
  background-size: 40px 40px;
  opacity: 0.5;
}

/* Content */
.content-wrapper {
  position: relative;
  z-index: 10;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.hero-section {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 3rem 1.5rem;
}

/* Hero card */
.hero-card {
  max-width: 1200px;
  width: 100%;
  background: rgba(255, 255, 255, 0.03);
  backdrop-filter: blur(30px);
  border: 1px solid rgba(255, 255, 255, 0.08);
  border-radius: 40px;
  padding: 4rem 3rem;
  box-shadow: 
    0 25px 50px -12px rgba(0, 0, 0, 0.5),
    inset 0 1px 0 rgba(255, 255, 255, 0.1);
  position: relative;
  animation: cardSlideUp 1s cubic-bezier(0.16, 1, 0.3, 1) forwards;
}

@keyframes cardSlideUp {
  from {
    opacity: 0;
    transform: translateY(60px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* Premium badge */
.premium-badge {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.5rem 1.25rem;
  background: rgba(168, 85, 247, 0.15);
  border: 1px solid rgba(168, 85, 247, 0.3);
  border-radius: 50px;
  margin: 0 auto 2rem;
  display: flex;
  width: fit-content;
  animation: badgePulse 3s ease-in-out infinite;
}

@keyframes badgePulse {
  0%, 100% { box-shadow: 0 0 0 0 rgba(168, 85, 247, 0.4); }
  50% { box-shadow: 0 0 0 8px rgba(168, 85, 247, 0); }
}

.badge-icon {
  font-size: 1.25rem;
  animation: badgeIconSpin 4s linear infinite;
}

@keyframes badgeIconSpin {
  0%, 90%, 100% { transform: rotate(0deg); }
  95% { transform: rotate(360deg); }
}

.badge-text {
  font-size: 0.875rem;
  font-weight: 600;
  color: rgba(168, 85, 247, 1);
  text-transform: uppercase;
  letter-spacing: 1px;
}

/* Hero icon */
.hero-icon {
  position: relative;
  width: 120px;
  height: 120px;
  margin: 0 auto 3rem;
}

.icon-ring {
  position: absolute;
  inset: 0;
  border: 2px solid;
  border-radius: 50%;
  animation: ringPulse 3s ease-in-out infinite;
}

.ring-1 {
  border-color: rgba(168, 85, 247, 0.6);
  animation-delay: 0s;
}

.ring-2 {
  border-color: rgba(236, 72, 153, 0.4);
  animation-delay: 1s;
}

.ring-3 {
  border-color: rgba(168, 85, 247, 0.3);
  animation-delay: 2s;
}

@keyframes ringPulse {
  0% {
    transform: scale(1);
    opacity: 1;
  }
  100% {
    transform: scale(1.8);
    opacity: 0;
  }
}

.icon-content {
  position: absolute;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 4rem;
  animation: iconFloat 4s ease-in-out infinite;
  filter: drop-shadow(0 10px 30px rgba(168, 85, 247, 0.6));
}

@keyframes iconFloat {
  0%, 100% { transform: translateY(0) rotate(0deg); }
  50% { transform: translateY(-15px) rotate(5deg); }
}

/* Title */
.hero-title {
  text-align: center;
  margin-bottom: 2rem;
  line-height: 1.1;
}

.title-line {
  display: block;
  font-size: clamp(3rem, 8vw, 6rem);
  font-weight: 900;
  color: rgba(255, 255, 255, 0.95);
  letter-spacing: -2px;
  text-shadow: 0 0 40px rgba(168, 85, 247, 0.5);
}

.title-accent {
  background: linear-gradient(135deg, #a855f7 0%, #ec4899 50%, #a855f7 100%);
  background-size: 200% 200%;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  animation: titleShimmer 4s ease-in-out infinite;
}

@keyframes titleShimmer {
  0%, 100% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
}

/* Subtitle */
.hero-subtitle {
  text-align: center;
  font-size: clamp(1.125rem, 2.5vw, 1.5rem);
  color: rgba(255, 255, 255, 0.75);
  line-height: 1.8;
  max-width: 800px;
  margin: 0 auto 4rem;
  font-weight: 300;
}

.highlight {
  font-weight: 600;
  position: relative;
  display: inline-block;
}

.highlight-pink {
  color: #ec4899;
  text-shadow: 0 0 20px rgba(236, 72, 153, 0.4);
}

.highlight-purple {
  color: #a855f7;
  text-shadow: 0 0 20px rgba(168, 85, 247, 0.4);
}

/* Feature grid */
.feature-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 2rem;
  margin-bottom: 4rem;
  max-width: 900px;
  margin-left: auto;
  margin-right: auto;
}

.feature-card {
  position: relative;
  padding: 2.5rem 2rem;
  background: rgba(255, 255, 255, 0.05);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
  border-radius: 28px;
  cursor: pointer;
  overflow: hidden;
  transition: all 0.5s cubic-bezier(0.16, 1, 0.3, 1);
}

.feature-card:hover {
  transform: translateY(-12px) scale(1.02);
  border-color: rgba(255, 255, 255, 0.2);
  box-shadow: 0 30px 60px rgba(168, 85, 247, 0.3);
}

.feature-background {
  position: absolute;
  inset: 0;
  opacity: 0;
  transition: opacity 0.5s ease;
}

.card-browse:hover .feature-background {
  background: radial-gradient(circle at center, rgba(168, 85, 247, 0.15) 0%, transparent 70%);
  opacity: 1;
}

.card-report:hover .feature-background {
  background: radial-gradient(circle at center, rgba(236, 72, 153, 0.15) 0%, transparent 70%);
  opacity: 1;
}

.feature-content {
  position: relative;
  z-index: 1;
}

.feature-icon-wrapper {
  position: relative;
  width: 80px;
  height: 80px;
  margin: 0 auto 1.5rem;
  display: flex;
  align-items: center;
  justify-content: center;
}

.feature-icon {
  font-size: 3rem;
  transition: transform 0.5s cubic-bezier(0.16, 1, 0.3, 1);
  filter: drop-shadow(0 8px 20px rgba(168, 85, 247, 0.4));
}

.feature-card:hover .feature-icon {
  transform: scale(1.2) rotate(5deg);
}

.icon-sparkle {
  position: absolute;
  width: 8px;
  height: 8px;
  background: #fff;
  border-radius: 50%;
  opacity: 0;
  animation: sparkle 2s ease-in-out infinite;
}

.sparkle-1 { top: 10%; right: 10%; animation-delay: 0s; }
.sparkle-2 { bottom: 15%; left: 15%; animation-delay: 0.7s; }
.sparkle-3 { top: 50%; right: 5%; animation-delay: 1.4s; }

@keyframes sparkle {
  0%, 100% {
    opacity: 0;
    transform: scale(0);
  }
  50% {
    opacity: 1;
    transform: scale(1);
  }
}

.feature-card:hover .icon-sparkle {
  animation-play-state: running;
}

.feature-title {
  font-size: 1.5rem;
  font-weight: 700;
  color: white;
  margin-bottom: 0.75rem;
  text-align: center;
}

.feature-description {
  font-size: 0.9375rem;
  color: rgba(255, 255, 255, 0.6);
  text-align: center;
  line-height: 1.6;
}

.feature-arrow {
  position: absolute;
  bottom: 1.5rem;
  right: 1.5rem;
  font-size: 1.5rem;
  color: rgba(255, 255, 255, 0.4);
  transition: all 0.3s ease;
}

.feature-card:hover .feature-arrow {
  color: white;
  transform: translateX(5px);
}

.card-shine {
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.1), transparent);
  transition: left 0.6s ease;
}

.feature-card:hover .card-shine {
  left: 100%;
}

/* Stats section */
.stats-section {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 3rem;
  padding: 2rem;
  background: rgba(255, 255, 255, 0.03);
  border: 1px solid rgba(255, 255, 255, 0.08);
  border-radius: 20px;
  max-width: 700px;
  margin: 0 auto 2rem;
}

.stat-item {
  text-align: center;
}

.stat-number {
  font-size: 2.5rem;
  font-weight: 800;
  background: linear-gradient(135deg, #a855f7, #ec4899);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin-bottom: 0.25rem;
}

.stat-label {
  font-size: 0.875rem;
  color: rgba(255, 255, 255, 0.6);
  font-weight: 500;
}

.stat-divider {
  width: 1px;
  height: 50px;
  background: rgba(255, 255, 255, 0.15);
}

/* Decorative dots */
.decorative-dots {
  display: flex;
  justify-content: center;
  gap: 0.75rem;
}

.dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: rgba(168, 85, 247, 0.8);
  animation: dotPulse 2s ease-in-out infinite;
}

.dot-1 { animation-delay: 0s; }
.dot-2 { animation-delay: 0.3s; }
.dot-3 { animation-delay: 0.6s; }

@keyframes dotPulse {
  0%, 100% {
    opacity: 0.3;
    transform: scale(1);
  }
  50% {
    opacity: 1;
    transform: scale(1.5);
  }
}

/* Scroll indicator */
.scroll-indicator {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
  margin-top: 3rem;
  opacity: 0.6;
  animation: scrollBounce 2s ease-in-out infinite;
}

@keyframes scrollBounce {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(10px); }
}

.scroll-icon {
  width: 24px;
  height: 36px;
  border: 2px solid rgba(255, 255, 255, 0.5);
  border-radius: 12px;
  position: relative;
}

.scroll-icon::before {
  content: '';
  position: absolute;
  top: 6px;
  left: 50%;
  transform: translateX(-50%);
  width: 4px;
  height: 8px;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 2px;
  animation: scrollDot 2s ease-in-out infinite;
}

@keyframes scrollDot {
  0%, 100% { transform: translateX(-50%) translateY(0); opacity: 1; }
  50% { transform: translateX(-50%) translateY(12px); opacity: 0; }
}

.scroll-text {
  font-size: 0.75rem;
  color: rgba(255, 255, 255, 0.5);
  text-transform: uppercase;
  letter-spacing: 1px;
}

/* Responsive */
@media (max-width: 768px) {
  .hero-card {
    padding: 3rem 2rem;
    border-radius: 32px;
  }

  .feature-grid {
    grid-template-columns: 1fr;
    gap: 1.5rem;
  }

  .stats-section {
    flex-direction: column;
    gap: 2rem;
    padding: 1.5rem;
  }

  .stat-divider {
    width: 80px;
    height: 1px;
  }

  .scroll-indicator {
    margin-top: 2rem;
  }
}
</style>