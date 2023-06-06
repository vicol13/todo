package com.vvi.todo.controller.impl;

import com.vvi.todo.dto.Board;
import com.vvi.todo.dto.Task;
import com.vvi.todo.dto.views.BoardView;
import com.vvi.todo.dto.views.TaskView;
import com.vvi.todo.service.BoardService;
import com.vvi.todo.service.TaskService;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/boards")
@AllArgsConstructor
public class BoardControllerImpl {

	private final BoardService service;
	private final TaskService taskService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@Timed("boards.get-all")
	public List<BoardView> getBoards() {
		return service.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	@Timed("boards.get-by-id")
	public BoardView getById(@PathVariable UUID id) {
		return service.getView(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	@Timed("boards.create")
	public BoardView createBoards(@NotNull @RequestBody Board board) {
		return service.saveBoard(board);
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@DeleteMapping("/{id}")
	@Timed("boards.delete")
	public void delete(@PathVariable UUID id) {
		service.deleteById(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/{id}/tasks")
	@Timed("task.create")
	public TaskView createTask(
		@NotNull @RequestBody Task task,
		@NotNull @PathVariable UUID id
	) {
		return taskService.createTask(task, id);
	}
}
