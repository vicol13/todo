package com.vvi.todo.service.imp;

import com.vvi.todo.dto.Task;
import com.vvi.todo.dto.views.SkinnyTaskView;
import com.vvi.todo.dto.views.TaskView;
import com.vvi.todo.entity.BoardEntity;
import com.vvi.todo.entity.TaskEntity;
import com.vvi.todo.entity.TaskStatus;
import com.vvi.todo.exception.NotFoundException;
import com.vvi.todo.mapper.TaskMapper;
import com.vvi.todo.repo.TaskRepository;
import com.vvi.todo.service.BoardService;
import com.vvi.todo.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.vvi.todo.TestingUtil.mockList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

	private final TaskRepository repository = mock(TaskRepository.class);
	private final TaskMapper mapper = mock(TaskMapper.class);
	private final BoardService boardService = mock(BoardService.class);

	private TaskService service;

	private final UUID id = UUID.fromString("853f58d3-4e56-4c74-92b2-128e1c8cf57c");

	@BeforeEach
	public void init() {
		service = new TaskServiceImpl(repository, mapper, boardService);
	}

	@Test
	@DisplayName("Should return return BoardView by id")
	void testGetTaskById() {
		//given
		Optional<TaskView> view = Optional.of(mock(TaskView.class));
		doReturn(view)
			.when(repository)
			.findTaskById(id);

		//when
		TaskView result = service.getTaskViewById(id);

		//then
		Assertions.assertEquals(view.get(), result);
		verify(repository).findTaskById(id);
	}

	@Test
	@DisplayName("Should throw NotFoundException on non existing record")
	public void testGetTaskByIdOnError() {
		//given
		doThrow(new NotFoundException("Unable to find task with id " + id.toString()))
			.when(repository)
			.findTaskById(id);

		//when
		NotFoundException ex = assertThrows(NotFoundException.class, () -> service.getTaskViewById(id));

		//then
		assertTrue(ex.getMessage().contains(id.toString()));
	}

	@Test
	@DisplayName("Should return list of SkinnyTaskView which belongs to a board")
	void getTasksByBoardId() {
		//given
		List<SkinnyTaskView> views = mockList(SkinnyTaskView.class, 5);
		doReturn(views).when(repository).findTaskByBoardId(id);

		//when
		List<SkinnyTaskView> result = service.getTasksByBoardId(id);

		//then
		assertEquals(5, result.size());
		verify(repository).findTaskByBoardId(id);

	}

	@Test
	@DisplayName("Should delete the task by id")
	void deleteTask() {
		//given
		doReturn(true).when(repository).existsById(id);
		doNothing().when(repository).deleteById(id);

		//when
		service.deleteTask(id);

		//then
		verify(repository).deleteById(id);
	}

	@Test
	@DisplayName("Should create task and return view of the task")
	void createTask() {
		//given
		BoardEntity board = mock(BoardEntity.class);
		Task task = mock(Task.class);
		TaskEntity entity = mock(TaskEntity.class);
		TaskView view = mock(TaskView.class);

		doReturn(entity).when(mapper).map(task, board);
		doReturn(entity).when(repository).saveAndFlush(entity);
		doReturn(board).when(boardService).getEntity(id);
		doReturn(view).when(mapper).map(entity);

		//when
		TaskView result = service.createTask(task, id);

		//then
		assertEquals(view, result);
		verify(repository).saveAndFlush(entity);
		verify(boardService).getEntity(id);
		verify(mapper).map(entity);

	}

	@Test
	@DisplayName("Should update task without changing the board")
	void updateTask() {
		//given
		Task task = new Task("some-name", UUID.randomUUID(), "some-description", TaskStatus.STARTED, null);
		TaskEntity entity = mock(TaskEntity.class);

		doReturn(Optional.of(entity))
			.when(repository)
			.findById(id);

		doReturn(entity)
			.when(repository)
			.saveAndFlush(entity);

		doReturn(mock(TaskView.class))
			.when(mapper)
			.map(entity);

		// when
		TaskView result = service.updateTask(id, task);

		// then
		verify(entity, times(0)).setBoard(any());
		verify(entity, times(1)).setStatus(task.getStatus());
		verify(entity, times(1)).setName(task.getName());
		verify(entity, times(1)).setDescription(task.getDescription());
		verify(repository).saveAndFlush(entity);


	}

	@Test
	@DisplayName("Should update task without changing the board and change the board")
	void updateTaskAndChangeTheBoard() {
		//given
		UUID boardId = UUID.randomUUID();
		Task task = new Task("some-name", UUID.randomUUID(), "some-description", TaskStatus.STARTED, boardId);
		TaskEntity entity = mock(TaskEntity.class);
		BoardEntity board = mock(BoardEntity.class);

		doReturn(Optional.of(entity))
			.when(repository)
			.findById(id);

		doReturn(board)
			.when(boardService)
			.getEntity(boardId);

		doReturn(entity)
			.when(repository)
			.saveAndFlush(entity);

		doReturn(mock(TaskView.class))
			.when(mapper)
			.map(entity);

		// when
		TaskView result = service.updateTask(id, task);

		// then
		verify(entity, times(1)).setBoard(board);
		verify(entity, times(1)).setStatus(task.getStatus());
		verify(entity, times(1)).setName(task.getName());
		verify(entity, times(1)).setDescription(task.getDescription());
		verify(repository).saveAndFlush(entity);

	}

	@Test
	@DisplayName("Should patch one field")
	void patchTask() {
		//given
		TaskEntity entity = mock(TaskEntity.class);
		doReturn(Optional.of(entity))
			.when(repository)
			.findById(id);
		doReturn(entity)
			.when(repository)
			.saveAndFlush(entity);
		doReturn(mock(TaskView.class))
			.when(mapper)
			.map(entity);
		//when
		TaskView result = service.patchTask(id, Map.of("name", "new name"));

		//then
		verify(entity, times(1)).setName(eq("new name"));
		verify(repository).saveAndFlush(entity);
	}

	@Test
	@DisplayName("Should patch task multiple fields")
	void testPatchTaskWithMultipleFields() {
		//given
		TaskEntity entity = mock(TaskEntity.class);
		doReturn(Optional.of(entity))
			.when(repository)
			.findById(id);
		doReturn(entity)
			.when(repository)
			.saveAndFlush(entity);
		doReturn(mock(TaskView.class))
			.when(mapper)
			.map(entity);
		//when
		TaskView result = service.patchTask(id, Map.of("name", "new name", "description", "new description"));

		//then
		verify(entity, times(1)).setName(eq("new name"));
		verify(entity, times(1)).setDescription(eq("new description"));
		verify(repository).saveAndFlush(entity);
	}


	@Test
	@DisplayName("Should throw exception of patchin non-existing field")
	void testPatchTaskOnError() {
		//given
		TaskEntity entity = mock(TaskEntity.class);
		doReturn(Optional.of(entity))
			.when(repository)
			.findById(id);
		doReturn(entity)
			.when(repository)
			.saveAndFlush(entity);
		doReturn(mock(TaskView.class))
			.when(mapper)
			.map(entity);
		//when
		IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class,
			() -> service.patchTask(id, Map.of("name", "new name", "description", "new description", "bad-field", "aaaa")));

		//then
		assertTrue(ex.getMessage().contains("bad-field"));
		verify(repository, times(0)).saveAndFlush(entity);
	}
}

