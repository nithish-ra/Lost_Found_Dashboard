import api from "./api";

export const UserService = {
  getById(id) {
    return api.get(`/users/${id}`);
  },
  create(data) {
    return api.post("/users", data);
  },
  update(id, data) {
    return api.put(`/users/${id}`, data);
  },
  delete(id) {
    return api.delete(`/users/${id}`);
  },
  login(credentials) {
  return api.post("/users/login", credentials);
}

};
