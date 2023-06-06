package com.vvi.todo.dto.views;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface BoardView {
	UUID getId();
	String getName();
	String getDescription();
	@Value("#{@taskServiceImpl.getTasksByBoardId(target.id)}")
	List<SkinnyTaskView> getTasks();
}
