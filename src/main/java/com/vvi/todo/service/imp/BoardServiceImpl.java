package com.vvi.todo.service.imp;

import com.vvi.todo.dto.Board;
import com.vvi.todo.dto.views.BoardView;
import com.vvi.todo.entity.BoardEntity;
import com.vvi.todo.exception.NotFoundException;
import com.vvi.todo.mapper.BoardMapper;
import com.vvi.todo.repo.BoardRepository;
import com.vvi.todo.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
	private final BoardRepository repository;
	private final BoardMapper mapper;

	@Override
	public BoardView getView(final UUID id) throws NotFoundException {
		return repository.findBoardProjectedById(id)
			.orElseThrow(() -> new NotFoundException("Unable to find board with id: " + id));
	}

	@Override
	public BoardEntity getEntity(final UUID id) throws NotFoundException {
		return repository.findById(id)
			.orElseThrow(() -> new NotFoundException("Unable to find board with id: " + id));
	}

	@Override
	public List<BoardView> findAll() {
		return repository.findAllProjectedBy();
	}

	@Override
	public void deleteById(final UUID id) throws NotFoundException {
		if (repository.existsById(id)) {
			log.debug("Deleting board by id");
			repository.deleteById(id);
		} else {
			throw new NotFoundException("Unable to find board with id: " + id);
		}
	}

	@Override
	public BoardView saveBoard(final Board board) {
		BoardEntity entity = repository.saveAndFlush(mapper.map(board));
		return mapper.map(entity);
	}

}
