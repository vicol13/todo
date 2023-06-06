package com.vvi.todo.entity;

import com.vvi.todo.exception.NotFoundException;

import java.util.Map;

public enum TaskStatus {
	CREATED,
	STARTED,
	COMPLETED;

	private final static Map<String, TaskStatus> map = Map.of(
		"created", CREATED,
		"started", STARTED,
		"completed", COMPLETED
	);

	public static TaskStatus fromString(final String input) {
		if (map.containsKey(input.toLowerCase())) {
			return map.get(input);
		}
		throw new IllegalArgumentException("No such status : " + input);
	}


}
