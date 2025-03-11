package org.zerock.stockspring.portfolio.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.stockspring.portfolio.dto.BalanceResponseDTO;
import org.zerock.stockspring.portfolio.service.PortfolioService;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping("/totest")
    public ResponseEntity<BalanceResponseDTO> getBalance(){
        BalanceResponseDTO response = portfolioService.getBalanceService();
        log.info("테스트");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
