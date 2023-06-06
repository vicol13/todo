package com.vvi.todo.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.vvi.todo.dto.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {
	public User map(final JsonNode root, final UUID id) {
		return User.builder()
			.email(root.get("email").textValue())
			.username(root.get("login").get("username").textValue())
			.fullName(root.get("name").get("first").textValue() + " " + root.get("name").get("last").textValue())
			.id(id)
			.build();
	}
}
