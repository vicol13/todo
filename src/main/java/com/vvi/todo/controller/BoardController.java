package com.vvi.todo.controller;

import com.vvi.todo.dto.Board;
import com.vvi.todo.dto.Task;
import com.vvi.todo.dto.views.BoardView;
import com.vvi.todo.dto.views.TaskView;
import com.vvi.todo.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

public interface BoardController {
	@Operation(
		description = "Returns all existing boards in the db of boards"
	)
	List<BoardView> getBoards();

	@Operation(
		description = "Returns by given id",

		parameters = {
			@Parameter(name = "id", description = "represent id of the task")
		},
		responses = {
			@ApiResponse(
				description = "When board is not found",
				responseCode = "404",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class))
			),
			@ApiResponse(
				description = "When board is present in the database",
				responseCode = "200",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BoardView.class))
			)
		}
	)
	BoardView getById(final UUID id);

	@Operation(
		description = "Based on input creates a board and return its views"
	)
	BoardView createBoards(final Board board);

	@Operation(
		description = "Deletes a board",
		responses = {
			@ApiResponse(
				description = "When board is not found",
				responseCode = "404",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class))
			)
		}
	)
	void delete(final UUID id);

	@Operation(
		description = "creates a task under the given board",
		responses = {
			@ApiResponse(
				description = "When board is not found",
				responseCode = "404",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class))
			)
		}
	)
	TaskView createTask(final Task task, final UUID id);
}
