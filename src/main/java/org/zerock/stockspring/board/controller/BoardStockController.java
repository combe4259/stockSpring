package org.zerock.stockspring.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.stockspring.board.dto.BoardDTO;
import org.zerock.stockspring.board.dto.PageRequestDTO;
import org.zerock.stockspring.board.dto.PageResponseDTO;
import org.zerock.stockspring.board.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@Log4j2
@RequiredArgsConstructor
public class BoardStockController {

    private final BoardService boardService;

    @GetMapping("/stock/{stockCode}")
    public PageResponseDTO<BoardDTO> getStockBoardList(@PathVariable("stockCode") Long stockCode, PageRequestDTO pageRequestDTO){
        log.info("=========Stock Board API List: " + stockCode + "========");
        PageResponseDTO<BoardDTO> responseDTO = boardService.listByStockCode(stockCode, pageRequestDTO);
        return responseDTO;
    }
}
