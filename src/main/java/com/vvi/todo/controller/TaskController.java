package com.vvi.todo.controller;

import com.vvi.todo.dto.Task;
import com.vvi.todo.dto.views.TaskView;
import com.vvi.todo.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.UUID;

@Tag(name = "task-controller", description = "Contains all endpoints related to tasks")
public interface TaskController {

	@Operation(
		description = "Returns by given id",

		parameters = {
			@Parameter(name = "id", description = "represent id of the task")
		},
		responses = {
			@ApiResponse(
				description = "When task is not found",
				responseCode = "404",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class))
			),
			@ApiResponse(
				description = "When task is present in the database",
				responseCode = "200",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TaskView.class))
			)
		}
	)
	TaskView getTaskById(UUID id);


	@Operation(
		description = "Overittes existing task in db",

		requestBody = @RequestBody(
			content = @Content(schema = @Schema(implementation = Task.class))
		),
		responses = {
			@ApiResponse(
				description = "Task which had be overitten is not existing",
				responseCode = "404",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class))
			),
			@ApiResponse(
				description = "When task is present in the database",
				responseCode = "200",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TaskView.class))
			)
		}
	)
	TaskView update(UUID id, Task task);

	@Operation(
		description = "Patch existing task in db",
		requestBody = @RequestBody(description = "expects a json where key is field name which have to patched and value is new value "),
		responses = {
			@ApiResponse(
				description = "Task which had be patched is not existing",
				responseCode = "404",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessage.class))
			),
			@ApiResponse(
				description = "When task is present in the database",
				responseCode = "200",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TaskView.class))
			)
		}
	)
	TaskView patchTask(final UUID id, final Map<String, String> fieldsUpdates);

}
