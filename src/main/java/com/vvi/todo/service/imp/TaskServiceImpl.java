package com.vvi.todo.service.imp;

import com.vvi.todo.dto.Task;
import com.vvi.todo.dto.views.SkinnyTaskView;
import com.vvi.todo.dto.views.TaskView;
import com.vvi.todo.entity.BoardEntity;
import com.vvi.todo.entity.TaskEntity;
import com.vvi.todo.entity.TaskStatus;
import com.vvi.todo.exception.BadRequestException;
import com.vvi.todo.exception.NotFoundException;
import com.vvi.todo.mapper.TaskMapper;
import com.vvi.todo.repo.TaskRepository;
import com.vvi.todo.service.BoardService;
import com.vvi.todo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskRepository repository;
	private final TaskMapper mapper;
	private final BoardService boardService;

	private final Map<String, BiConsumer<TaskEntity, String>> setterMap;

	public TaskServiceImpl(
		final TaskRepository repository,
		final TaskMapper mapper,
		final BoardService boardService

	) {
		this.repository = repository;
		this.mapper = mapper;
		this.boardService = boardService;
		this.setterMap = Map.of(
			"name", TaskEntity::setName,
			"description", TaskEntity::setDescription,
			"status", (task, newStatus) -> task.setStatus(TaskStatus.fromString(newStatus)),
			"board", (task, boardId) -> task.setBoard(this.boardService.getEntity(UUID.fromString(boardId)))
		);
	}

	@Override
	public TaskView getTaskViewById(final UUID id) throws NotFoundException {
		return repository.findTaskById(id)
			.orElseThrow(() -> new NotFoundException("Unable to find task with id :" + id));
	}

	@Override
	public TaskEntity getTaskEntityById(final UUID id) throws NotFoundException {
		return repository.findById(id)
			.orElseThrow(() -> new NotFoundException("Unable to find task with id :" + id));

	}


	@Override
	public List<SkinnyTaskView> getTasksByBoardId(UUID boardId) {
		return repository.findTaskByBoardId(boardId);
	}

	@Override
	public void deleteTask(UUID id) throws NotFoundException {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new NotFoundException("Unable to find task with id :" + id);
		}
	}

	@Override
	public TaskView createTask(final Task task, final UUID boardId) {
		BoardEntity board = boardService.getEntity(boardId);
		TaskEntity entity = repository.saveAndFlush(mapper.map(task, board));
		return mapper.map(entity);
	}

	@Override
	public TaskView updateTask(final UUID id, final Task task) {
		TaskEntity entity = getTaskEntityById(id);

		if (task.getBoardId() != null) {
			entity.setBoard(boardService.getEntity(task.getBoardId()));
		}

		entity.setName(task.getName());
		entity.setDescription(task.getDescription());
		entity.setStatus(task.getStatus() == null ? entity.getStatus() : task.getStatus());

		return mapper.map(repository.saveAndFlush(entity));
	}

	@Override
	public TaskView patchTask(final UUID id, final Map<String, String> task) throws BadRequestException, IllegalArgumentException, NotFoundException {
		TaskEntity entity = getTaskEntityById(id);

		for (Map.Entry<String, String> entry : task.entrySet()) {
			if (!setterMap.containsKey(entry.getKey())) {
				throw new IllegalStateException("Field [" + entry.getKey() + "] is no present in Task entity");
			}
			this.setterMap.get(entry.getKey()).accept(entity, entry.getValue());
		}

		return mapper.map(repository.saveAndFlush(entity));
	}


}
