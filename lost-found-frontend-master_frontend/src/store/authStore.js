import { defineStore } from "pinia";
import { UserService } from "../services/userService";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    user: null,   // Stores user data (name, email, role, etc.)
    token: null,  // Stores JWT or session token
  }),

  getters: {
    isAuthenticated: (state) => !!state.user,
    isAdmin: (state) => state.user?.isAdmin === true,
    isNormalUser: (state) => state.user?.isAdmin === false,
  },

  actions: {
    /**
     * Simulated or API-based login
     * You can connect this to your real backend through UserService.login()
     */
    async login(credentials) {
      try {
        // Example: Use UserService to send login request to backend
        const res = await UserService.login(credentials);

        // Assuming API returns { user, token }
        this.user = res.data.user;
        this.token = res.data.token;

        // Save session to localStorage
        localStorage.setItem(
          "auth",
          JSON.stringify({ user: this.user, token: this.token })
        );

        return true;
      } catch (error) {
        console.error("Login failed:", error);
        return false;
      }
    },

    /**
     * Create/Register new user
     */
    async register(userData) {
      try {
        const res = await UserService.create(userData);
        this.user = res.data;
        localStorage.setItem("auth", JSON.stringify({ user: this.user }));
      } catch (error) {
        console.error("Registration failed:", error);
      }
    },

    /**
     * Load user session from localStorage (auto-login)
     */
    loadFromStorage() {
      const stored = localStorage.getItem("auth");
      if (stored) {
        const { user, token } = JSON.parse(stored);
        this.user = user;
        this.token = token;
      }
    },

    /**
     * Logout — clears state and storage
     */
    logout() {
      this.user = null;
      this.token = null;
      localStorage.removeItem("auth");
    },
  },
});
