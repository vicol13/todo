package com.vvi.todo.dto.views;

import com.vvi.todo.dto.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public interface TaskView {
	UUID getId();
	String getName();
	String getDescription();
	String getStatus();
	@Value("#{@userServiceImpl.getUserById(target.userId).get() ?: null }")
	User getUser();
}
