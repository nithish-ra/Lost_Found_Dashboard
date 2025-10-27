package com.example.lostfound;

import com.example.lostfound.model.Item;
import com.example.lostfound.repository.ItemRepository;
import com.example.lostfound.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class ItemServiceTest {
    @Test
    public void testCreateAndGet() {
        ItemRepository repo = Mockito.mock(ItemRepository.class);
        Item saved = new Item();
        saved.setId("1");
        saved.setTitle("Wallet");
        Mockito.when(repo.save(any(Item.class))).thenReturn(saved);
        Mockito.when(repo.findById("1")).thenReturn(Optional.of(saved));

        ItemService svc = new ItemService(repo);
        Item out = svc.create(new Item());
        assertNotNull(out);
        assertEquals("1", out.getId());

        Item got = svc.getById("1");
        assertEquals("Wallet", got.getTitle());
    }
}
