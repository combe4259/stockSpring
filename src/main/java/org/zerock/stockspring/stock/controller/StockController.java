package org.zerock.stockspring.stock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.stockspring.stock.dto.DailyPriceResponseDTO;
import org.zerock.stockspring.stock.dto.QuotationResponseDTO;
import org.zerock.stockspring.stock.service.StockService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/domestic-stock")
public class StockController {

    private final StockService stockService;

    //현재가 시세 조회
    @GetMapping("/quotations")
    public ResponseEntity<QuotationResponseDTO> getPresent(String stockCode){
        QuotationResponseDTO response = stockService.getPresentService(stockCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //주식 데이터 월 일 년 분
    @GetMapping("/daily")
    public ResponseEntity<DailyPriceResponseDTO> getDailyPrice(String stockCode,String startDate,String endDate){
        DailyPriceResponseDTO response = stockService.getDailyPriceService(stockCode,startDate,endDate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
