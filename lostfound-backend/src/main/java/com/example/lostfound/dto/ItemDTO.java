package com.example.lostfound.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ItemDTO {
    private String id;

    @NotBlank(message = "Title is required")
    @Size(max = 120)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "lost|found", flags = Pattern.Flag.CASE_INSENSITIVE,
             message = "Status must be 'lost' or 'found'")
    private String status;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Contact required")
    private String contact;

    private String postedByUserId;

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

    
}
