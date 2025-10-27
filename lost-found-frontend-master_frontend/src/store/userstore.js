import { defineStore } from "pinia";
import { UserService } from "../services/userService.js";

export const useUserStore = defineStore("user", {
  state: () => ({
    users: [],
    user: JSON.parse(localStorage.getItem("user")) || null,
    token: localStorage.getItem("token") || null,
  }),
  getters: {
    isAuthenticated: (state) => !!state.user,
    isAdmin: (state) => state.user?.isAdmin === true,
    isNormalUser: (state) => state.user && state.user.isAdmin === false,
  }, 
  actions: {
    async createUser(userData) {
      try {
        const response = await UserService.create({ ...userData, isAdmin: false });
        this.users.push(response.data);
        alert("User registered successfully!");
        return true;
      } catch (error) {
        console.error("Registration failed:", error);
        alert("Error registering user!");
        return false;
      }
    },
    async login(credentials) {
      this.logout();
      if (credentials.email === "admin@board.com" && credentials.password === "password") {
        this.user = { name: "Admin User", email: credentials.email, isAdmin: true };
        this.token = "fake-jwt-token";
        localStorage.setItem("user", JSON.stringify(this.user));
        localStorage.setItem("token", this.token);
        return true;
      }
      try {
        const response = await UserService.login(credentials);
        this.user = response.data;
        this.token = "fake-jwt-token";
        localStorage.setItem("user", JSON.stringify(this.user));
        localStorage.setItem("token", this.token);
        return true;
      } catch (err) {
        console.error("Login failed:", err);
        alert("Invalid email or password!");
        return false;
      }
    },
    /**
     * Load users & current user from localStorage
     */
    loadFromStorage() {
        const storedUsers = localStorage.getItem("users");
        const storedUser = localStorage.getItem("user");
        const storedToken = localStorage.getItem("token");

        if (storedUsers) this.users = JSON.parse(storedUsers);
        if (storedUser) this.user = JSON.parse(storedUser);
        if (storedToken) this.token = storedToken;
      },
    logout() {
      this.user = null;
      this.token = null;
      localStorage.removeItem("user");
      localStorage.removeItem("token");
    },
  },
});
