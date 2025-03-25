package org.zerock.stockspring.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.stockspring.board.entity.Board;

public interface BoardSearch {
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
