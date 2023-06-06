package com.vvi.todo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
	private String username;
	private String email;
	private UUID id;
	private String fullName;
}
