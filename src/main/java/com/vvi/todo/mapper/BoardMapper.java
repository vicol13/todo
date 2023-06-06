package com.vvi.todo.mapper;

import com.vvi.todo.dto.Board;
import com.vvi.todo.dto.views.BoardView;
import com.vvi.todo.entity.BoardEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BoardMapper {
	private final ProjectionFactory pf;

	public BoardEntity map(final Board board) {
		BoardEntity entity = new BoardEntity();
		entity.setDescription(board.getDescription());
		entity.setName(board.getName());
		return entity;
	}

	public BoardView map(final BoardEntity entity) {
		return pf.createProjection(BoardView.class, entity);
	}
}
