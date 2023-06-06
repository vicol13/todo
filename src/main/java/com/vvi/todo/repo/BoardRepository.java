package com.vvi.todo.repo;

import com.vvi.todo.dto.views.BoardView;
import com.vvi.todo.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, UUID> {
	Optional<BoardView> findBoardProjectedById(final UUID id);
	List<BoardView> findAllProjectedBy();

}
