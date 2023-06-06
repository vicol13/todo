package com.vvi.todo.dto.views;

import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public interface SkinnyTaskView {
	UUID getId();
	String getName();
	String getStatus();
}
