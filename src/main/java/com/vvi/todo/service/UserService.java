package com.vvi.todo.service;


import com.vvi.todo.dto.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {


	/**
	 * Retrieves a user by their unique identifier.
	 *
	 * @param id the ID of the user
	 * @return the User object corresponding to the ID
	 */
	Optional<User> getUserById(final UUID id);
}
