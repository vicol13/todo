package com.vvi.todo.controller.impl;

import com.vvi.todo.controller.TaskController;
import com.vvi.todo.dto.Task;
import com.vvi.todo.dto.views.TaskView;
import com.vvi.todo.exception.BadRequestException;
import com.vvi.todo.service.TaskService;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks/{id}")
@AllArgsConstructor
public class TaskControllerImpl implements TaskController {

	private TaskService taskService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping()
	@Timed("tasks.get-by-id")
	public TaskView getTaskById(@PathVariable UUID id) {
		return taskService.getTaskViewById(id);
	}


	@ResponseStatus(HttpStatus.OK)
	@PutMapping()
	@Timed("tasks.update")
	public TaskView update(@PathVariable UUID id, @RequestBody Task task) {
		return taskService.updateTask(id, task);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping()
	@Timed("tasks.patch")
	public TaskView patchTask(
		@PathVariable UUID id,
		@RequestBody Map<String, String> fieldsUpdates
	) {
		if (fieldsUpdates.isEmpty()) {
			throw new BadRequestException("Body can't be empty");
		}
		return taskService.patchTask(id, fieldsUpdates);
	}
}
