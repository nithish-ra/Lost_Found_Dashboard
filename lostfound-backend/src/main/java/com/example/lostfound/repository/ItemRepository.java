package com.example.lostfound.repository;

import com.example.lostfound.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findByStatusIgnoreCase(String status);
    List<Item> findByPostedByUserId(String userId);
    List<Item> findByTitleContainingIgnoreCase(String q);
}
