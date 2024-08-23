package com.murita.place_service.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public record Place(
		Long id, String name, String slug, String state,
		@CreatedDate LocalDateTime createdAt, @LastModifiedDate LocalDateTime updatedAt) {

	public Place(long l, String name2, String state2, String slug2, String string, String string2) {
		// TODO Auto-generated constructor stub
	}
}
