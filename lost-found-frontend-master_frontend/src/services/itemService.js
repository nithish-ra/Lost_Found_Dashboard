import api from "./api";

export const ItemService = {
  getAll(params) {
    return api.get("/items", { params });
  },
  getById(id) {
    return api.get(`/items/${id}`);
  },
  create(data) {
    return api.post("/items", data);
  },
  update(id, data) {
    return api.put(`/items/${id}`, data);
  },
  delete(id) {
    return api.delete(`/items/${id}`);
  },
  getByUserId(userId) {
    return api.get("/items/users", { params: { userId } });
  }
};
