package com.example.lostfound.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String title;           // short title
    private String description;     // details
    private String status;          // "lost" or "found"
    private String location;        // where it was found / lost
    private String contact;         // phone/email
    private String postedByUserId; // reference to User.id (string)
    private Instant createdAt;
    private Instant updatedAt;

    public Item() {}


	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getPostedByUserId() {
		return postedByUserId;
	}


	public void setPostedByUserId(String postedByUserId) {
		this.postedByUserId = postedByUserId;
	}


	public Instant getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}


	public Instant getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}
