package com.vvi.todo.repo;

import com.vvi.todo.dto.views.SkinnyTaskView;
import com.vvi.todo.dto.views.TaskView;
import com.vvi.todo.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
	Optional<TaskView> findTaskById(final UUID id);
	List<SkinnyTaskView> findTaskByBoardId(final UUID id);


}
