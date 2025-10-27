package com.example.lostfound.service;

import com.example.lostfound.model.Item;
import com.example.lostfound.repository.ItemRepository;
import com.example.lostfound.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repo;

    public ItemService(ItemRepository repo) { this.repo = repo; }

    public Item create(Item item) {
        item.setCreatedAt(Instant.now());
        item.setUpdatedAt(Instant.now());
        return repo.save(item);
    }

    public Item update(String id, Item updated) {
        Item existing = repo.findById(id).orElseThrow(() -> new NotFoundException("Item not found"));
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());
        existing.setLocation(updated.getLocation());
        existing.setContact(updated.getContact());
        existing.setPostedByUserId(updated.getPostedByUserId());
        existing.setUpdatedAt(Instant.now());
        return repo.save(existing);
    }

    public Item getById(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Item not found"));
    }
    
    public List<Item> getByUserId(String id){
    	return repo.findByPostedByUserId(id);
    }

    public List<Item> listAll() {
        return repo.findAll();
    }

    public void delete(String id) {
        if (!repo.existsById(id)) throw new NotFoundException("Item not found");
        repo.deleteById(id);
    }

    public List<Item> findByStatus(String status) { return repo.findByStatusIgnoreCase(status); }

    public List<Item> searchTitle(String q) { return repo.findByTitleContainingIgnoreCase(q); }
}
