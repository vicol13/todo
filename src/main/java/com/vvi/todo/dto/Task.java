package com.vvi.todo.dto;

import com.vvi.todo.entity.TaskStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Class used for updating/creating entity
 */
@Data
@AllArgsConstructor
public class Task {

	@NotNull(message = "Name can not be null")
	private String name;

	@NotNull(message = "UserId can not be null")
	private UUID userId;

	@Nullable
	private String description;

	@Nullable
	private TaskStatus status;

	@Nullable
	private UUID boardId;

}
