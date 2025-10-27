<template>
    <header class="bg-white shadow-md">
        <nav class="container mx-auto px-6 py-4 flex justify-between items-center">
            <router-link :to="{ name: 'Home' }" class="text-2xl font-bold text-indigo-600 hover:text-indigo-800 transition">
                Lost & Found 🔎
            </router-link>
            <div class="space-x-4">
                <router-link :to="{ name: 'Items' }" class="text-gray-600 hover:text-indigo-600 font-medium">Board</router-link>
                <router-link :to="{ name: 'PostItem' }" class="text-gray-600 hover:text-indigo-600 font-medium">Post Item</router-link>
                
                <template v-if="authStore.isAdmin">
                    <router-link :to="{ name: 'AdminDashboard' }" class="px-3 py-2 bg-indigo-500 text-white rounded-lg hover:bg-indigo-600 font-medium">Admin</router-link>
                </template>
                <template v-else>
                    <router-link :to="{ name: 'Login' }" class="text-gray-600 hover:text-indigo-600 font-medium">Admin Login</router-link>
                </template>
            </div>
        </nav>
    </header>
</template>

<script setup>
import { useAuthStore } from '../store/authStore';
const authStore = useAuthStore();
</script>