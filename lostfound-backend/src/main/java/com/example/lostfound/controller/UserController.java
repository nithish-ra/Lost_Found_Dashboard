package com.example.lostfound.controller;

import com.example.lostfound.dto.UserDTO;
import com.example.lostfound.model.User;
import com.example.lostfound.service.UserService;
import com.example.lostfound.util.Mappers;
import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {
        User saved = service.create(Mappers.toUser(dto));
        return new ResponseEntity<>(Mappers.toUserDTO(saved), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable String id) {
        return Mappers.toUserDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable String id, @Valid @RequestBody UserDTO dto) {
        User updated = service.update(id, Mappers.toUser(dto));
        return Mappers.toUserDTO(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO credentials) {
        Optional<User> userOpt = service.findByEmail(credentials.getEmail());

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(credentials.getPassword())) {
            return ResponseEntity.ok(Mappers.toUserDTO(userOpt.get()));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }



}
