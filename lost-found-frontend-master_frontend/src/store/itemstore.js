import { defineStore } from "pinia";
import { ItemService } from "../services/itemService";

export const useItemStore = defineStore("item", {
  state: () => ({
    items: [],
    loading: false,
    error: null,
  }),
  actions: {
    async fetchItems() {
      this.loading = true;
      try {
        const res = await ItemService.getAll();
        this.items = res.data;
      } catch (err) {
        this.error = err.message;
      } finally {
        this.loading = false;
      }
    },
    async addItem(item) {
      const res = await ItemService.create(item);
      this.items.push(res.data);
    },
    async updateItem(id, item) {
      const res = await ItemService.update(id, item);
      const idx = this.items.findIndex(i => i.id === id);
      if (idx > -1) this.items[idx] = res.data;
    },
    async deleteItem(id) {
      await ItemService.delete(id);
      this.items = this.items.filter(i => i.id !== id);
    },
  },
});
