package com.example.lostfound.service;

import com.example.lostfound.model.User;
import com.example.lostfound.repository.UserRepository;
import com.example.lostfound.exception.NotFoundException;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public User create(User u) {
        return repo.save(u);
    }

    public User getById(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User update(String id, User updated) {
        User existing = repo.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        return repo.save(existing);
    }

    public void delete(String id) {
        if (!repo.existsById(id)) throw new NotFoundException("User not found");
        repo.deleteById(id);
    }
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

}
