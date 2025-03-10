package org.zerock.stockspring.portfolio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/domestic-stock")
public class PortfolioController {

//    private final PortfolioService portfolioService;

    //주식잔고조회
    @GetMapping("/trading")
    public ResponseEntity<tmp> getBalance(){
        tmp tmp = portfolioService.findBalance();
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

}
