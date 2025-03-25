package org.zerock.stockspring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.stockspring.board.entity.Board;
import org.zerock.stockspring.board.service.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

}
