package com.vvi.todo.service;

import com.vvi.todo.dto.Task;
import com.vvi.todo.dto.views.SkinnyTaskView;
import com.vvi.todo.dto.views.TaskView;
import com.vvi.todo.entity.TaskEntity;
import com.vvi.todo.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TaskService {
	/**
	 * Retrieve entity from database
	 *
	 * @param id of task entity
	 * @return TaskView object
	 * @throws NotFoundException when there is no such entity with @param id in database
	 */
	TaskView getTaskViewById(final UUID id) throws NotFoundException;

	TaskEntity getTaskEntityById(final UUID id) throws NotFoundException;

	/**
	 * Returns a list skinny views of tasks which belong to one board
	 *
	 * @param boardId id of the bo
	 * @return list of SkinnyTaskViews
	 * @throws NotFoundException when there is no such entity with @param boardId in database
	 */
	List<SkinnyTaskView> getTasksByBoardId(final UUID boardId) throws NotFoundException;

	/**
	 * Deletes the entity by id
	 *
	 * @param id of entity
	 * @throws NotFoundException when there is no such entity in db with @param id
	 */

	void deleteTask(final UUID id) throws NotFoundException;

	/**
	 * Creates a new task under given board
	 * @param task data
	 * @param boardId to which current task belong
	 * @return a view of newly created task
	 */

	TaskView createTask(final Task task, final UUID boardId);

	/**
	 * Overwrites existing task
	 * @param id of task which will be Overwritten
	 * @param task object which contains data about task
	 * @return a view of updated task
	 */
	TaskView updateTask(final UUID id, final Task task);

	/**
	 * Patches a task with desired fields
	 * @param id of the task
	 * @param fields map of next format {'field-name':'new-value'}
	 * @return a view of newly updated task
	 */
	TaskView patchTask(final UUID id, final Map<String, String> fields);

}
