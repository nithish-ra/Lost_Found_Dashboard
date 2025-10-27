import { createRouter, createWebHistory } from "vue-router";
import Home from "../pages/Home.vue";
import ItemsPage from "../pages/ItemsPage.vue";
import UsersPage from "../pages/UsersPage.vue";
import LoginPage from "../pages/LoginPage.vue";
import UserDets from "../pages/UserDets.vue";
import AdminDashboard from "../pages/AdminDashboard.vue";

const routes = [
  { path: "/", redirect: "/users" },
  { path: "/home", name: "Home", component: Home },
  { path: "/items", name: "Items", component: ItemsPage },
  { path: "/users", name: "Users", component: UsersPage },
  { path: "/id", name: "UserDets", component: UserDets },
// Displays ItemForm
  { path: "/login", name: "Login", component: LoginPage },
  { path: "/admin", name: "AdminDashboard", component: AdminDashboard, meta: { requiresAdmin: true } },
];
export default createRouter({
  history: createWebHistory(),
  routes,
});
