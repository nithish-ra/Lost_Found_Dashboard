package com.example.lostfound.util;

import com.example.lostfound.dto.ItemDTO;
import com.example.lostfound.dto.UserDTO;
import com.example.lostfound.model.Item;
import com.example.lostfound.model.User;

import java.time.Instant;

public class Mappers {
    public static Item toItem(ItemDTO dto) {
        Item it = new Item();
        it.setId(dto.getId());
        it.setTitle(dto.getTitle());
        it.setDescription(dto.getDescription());
        it.setStatus(dto.getStatus());
        it.setLocation(dto.getLocation());
        it.setContact(dto.getContact());
        it.setPostedByUserId(dto.getPostedByUserId());
        if (it.getCreatedAt() == null) it.setCreatedAt(Instant.now());
        it.setUpdatedAt(Instant.now());
        return it;
    }

    public static ItemDTO toItemDTO(Item it) {
        ItemDTO d = new ItemDTO();
        d.setId(it.getId());
        d.setTitle(it.getTitle());
        d.setDescription(it.getDescription());
        d.setStatus(it.getStatus());
        d.setLocation(it.getLocation());
        d.setContact(it.getContact());
        d.setPostedByUserId(it.getPostedByUserId());
        return d;
    }

    public static User toUser(UserDTO dto) {
        User u = new User();
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        u.setPhone(dto.getPhone());
        u.setPassword(dto.getPassword());   // ✅ map password
        u.setAdmin(dto.isAdmin());          // ✅ map isAdmin
        return u;
    }

    public static UserDTO toUserDTO(User u) {
        UserDTO d = new UserDTO();
        d.setId(u.getId());
        d.setName(u.getName());
        d.setEmail(u.getEmail());
        d.setPhone(u.getPhone());
        d.setPassword(u.getPassword());     // ✅ map password
        d.setAdmin(u.isAdmin());            // ✅ map isAdmin
        return d;
    }

}
