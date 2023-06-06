package com.vvi.todo.mapper;

import com.vvi.todo.dto.Board;
import com.vvi.todo.dto.Task;
import com.vvi.todo.dto.views.TaskView;
import com.vvi.todo.entity.BoardEntity;
import com.vvi.todo.entity.TaskEntity;
import com.vvi.todo.entity.TaskStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskMapper {
	private final ProjectionFactory pf;

	public TaskEntity map(final Task task, final BoardEntity board) {
		return TaskEntity.builder()
			.board(board)
			.description(task.getDescription())
			.userId(task.getUserId())
			.status(TaskStatus.CREATED)
			.name(task.getName())
			.build();
	}


	public TaskView map(final TaskEntity entity) {
		return pf.createProjection(TaskView.class, entity);
	}
}
