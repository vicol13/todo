package com.vvi.todo.service.imp;

import com.fasterxml.jackson.databind.JsonNode;
import com.vvi.todo.dto.User;
import com.vvi.todo.mapper.UserMapper;
import com.vvi.todo.service.UserService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	private final RestTemplate template;
	private final UserMapper userMapper;

	public UserServiceImpl(
		@Qualifier("user-client") final RestTemplate template,
		final UserMapper userMapper
	) {
		this.template = template;
		this.userMapper = userMapper;
	}

	@Override
	@Timed("user.get-by-id")
	public Optional<User> getUserById(UUID id) {
		try {
			JsonNode root = template.getForObject("/api", JsonNode.class).get("results").get(0);
			return Optional.of(userMapper.map(root,id));
		} catch (RuntimeException e) {
			log.error("Error on retrieving the user with message {}", e.getMessage());
		}
		return Optional.empty();
	}

}
