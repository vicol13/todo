package com.vvi.todo.service;

import com.vvi.todo.dto.Board;
import com.vvi.todo.dto.views.BoardView;
import com.vvi.todo.entity.BoardEntity;
import com.vvi.todo.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface BoardService {
	BoardView getView(final UUID id) throws NotFoundException;

	BoardEntity getEntity(final UUID id) throws NotFoundException;

	List<BoardView> findAll();

	void deleteById(final UUID id) throws NotFoundException;

	BoardView saveBoard(final Board board);

}
