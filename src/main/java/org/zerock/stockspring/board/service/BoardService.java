package org.zerock.stockspring.board.service;

import org.zerock.stockspring.board.dto.BoardDTO;
import org.zerock.stockspring.board.dto.PageRequestDTO;
import org.zerock.stockspring.board.dto.PageResponseDTO;

public interface BoardService {

    //게시판 등록
    Long register(BoardDTO boardDTO);

    //게시판 조회
    BoardDTO readOne(Long bno);

    //게시판 수정
    void modify(BoardDTO boardDTO);

    //게시판 삭제
    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
