package org.zerock.stockspring.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.stockspring.board.entity.Board;
import org.zerock.stockspring.board.repository.BoardRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    //게시판 등록
    @Test
    public void testRegister(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Board board = Board.builder()
                    .title("title.."+i)
                    .content("content.."+i)
                    .writer("user.."+i)
                    .build();
            Board result = boardRepository.save(board);
            log.info("Bno"+result.getBno());
        });
    }
    //게시판 조회
    @Test
    public void readOneTest(){
        Long bno = 1L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info("게시판 조회 성공: "+ board);

    }
    //게시판 수정
    @Test
    public void modifyTest(){
        Long bno = 2L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = BoardDTO.builder()
                        .title("수정된 게시판")
                        .content("수정된 내용")
                        .build();

        board.change(boardDTO.getTitle(),boardDTO.getContent());
        log.info("게시판 수정 성공"+ board);
    }
    //게시판 삭제
    @Test
    public void removeTest(){
        Long bno = 50L;
        boardRepository.deleteById(bno);
        log.info("게시판 삭제 성공");
    }

}
