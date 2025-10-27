package com.example.lostfound.controller;

import com.example.lostfound.dto.ItemDTO;
import com.example.lostfound.model.Item;
import com.example.lostfound.service.ItemService;
import com.example.lostfound.util.Mappers;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ItemDTO> create(@Valid @RequestBody ItemDTO dto) {
        Item saved = service.create(Mappers.toItem(dto));
        return new ResponseEntity<>(Mappers.toItemDTO(saved), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ItemDTO> list(@RequestParam(value = "status", required = false) String status,
                              @RequestParam(value = "q", required = false) String q) {
        List<Item> items;
        if (status != null) items = service.findByStatus(status);
        else if (q != null) items = service.searchTitle(q);
        else items = service.listAll();

        return items.stream().map(Mappers::toItemDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ItemDTO get(@PathVariable String id) {
        return Mappers.toItemDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public ItemDTO update(@PathVariable String id, @Valid @RequestBody ItemDTO dto) {
        Item updated = service.update(id, Mappers.toItem(dto));
        return Mappers.toItemDTO(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/users/{userid}")
    public ResponseEntity<List<Item>> getByUser(@PathVariable String userid){
    	return new ResponseEntity<>(service.getByUserId(userid), HttpStatus.OK);
    }
    
}
