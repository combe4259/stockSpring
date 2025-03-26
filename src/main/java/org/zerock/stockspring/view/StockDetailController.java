package org.zerock.stockspring.view;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.zerock.stockspring.board.dto.BoardDTO;
import org.zerock.stockspring.board.dto.PageRequestDTO;
import org.zerock.stockspring.board.dto.PageResponseDTO;
import org.zerock.stockspring.board.service.BoardService;
import org.zerock.stockspring.stock.dto.QuotationResponseDTO;
import org.zerock.stockspring.stock.service.StockService;

//@Controller
//@RequiredArgsConstructor
//@Log4j2
//public class StockDetailController {
//
//    private final StockService stockService;
//    private final BoardService boardService;
//
//    @GetMapping("/stock/detail/{stockCode}")
//    public String stockDetail(@PathVariable String stockCode, @ModelAttribute PageRequestDTO pageRequestDTO, Model model){
//        QuotationResponseDTO quotationResponseDTO = stockService.getPresentService(stockCode);
//        model.addAttribute("quotation",quotationResponseDTO);
//
//        PageResponseDTO<BoardDTO> dtoList = boardService.listByStockCode(Long.parseLong(stockCode),pageRequestDTO);
//        model.addAttribute("dtoList",dtoList);
//
//        return "stock/detail";
//    }
//}

@Controller
@RequiredArgsConstructor
@Log4j2
public class StockDetailController {

    @GetMapping("/stock/detail/{stockCode}")
    public String stockDetail(@PathVariable String stockCode, Model model) {
        model.addAttribute("stockCode", stockCode);
        return "stock/detail";
    }
}