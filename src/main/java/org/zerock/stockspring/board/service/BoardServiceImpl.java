package org.zerock.stockspring.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.stockspring.board.dto.BoardDTO;
import org.zerock.stockspring.board.repository.BoardRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final ModelMapper
    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO){

    }

}
