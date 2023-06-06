package com.vvi.todo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Class used for creating updating actual entity
 */
@Data
public class Board {
	@NotNull(message = "Name can not be null")
	private String name;

	private String description;
}
