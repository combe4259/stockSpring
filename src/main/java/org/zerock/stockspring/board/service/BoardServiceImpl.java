package org.zerock.stockspring.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.stockspring.board.dto.BoardDTO;
import org.zerock.stockspring.board.dto.PageRequestDTO;
import org.zerock.stockspring.board.dto.PageResponseDTO;
import org.zerock.stockspring.board.entity.Board;
import org.zerock.stockspring.board.repository.BoardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    //게시판 등록 (게시물 번호 반환)
    @Override
    public Long register(BoardDTO boardDTO){
        Board board = modelMapper.map(boardDTO, Board.class);
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }
    //게시판 하나 조회
    @Override
    public BoardDTO readOne(Long bno){
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }

    //게시판 수정
    @Override
    public void modify(BoardDTO boardDTO){
         Optional<Board> result = boardRepository.findById(boardDTO.getBno());
         Board board = result.orElseThrow();
         board.change(boardDTO.getTitle(),boardDTO.getContent());
         boardRepository.save(board);
    }
    //게시물 삭제
    @Override
    public void remove(Long bno){
        boardRepository.deleteById(bno);
    }


    //게시판 특정 검색
    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO){
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");
        //pagination 적용 받아옴
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<BoardDTO> listByStockCode(Long stockCode, PageRequestDTO pageRequestDTO){
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result = boardRepository.searchByStockCode(stockCode, types, keyword, pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }


}
