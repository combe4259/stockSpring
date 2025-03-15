package org.zerock.stockspring.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class WebStockController {

    private final KisWebSocketService webSocketService;

    @PostMapping("/subscribe/{stockCode}")
    public String subscribeStock(@PathVariable String stockCode) {
        webSocketService.connectAndSubscribe(stockCode);
        return "종목 구독 요청 완료: " + stockCode;
    }

    @PostMapping("/disconnect")
    public String disconnect() {
        webSocketService.disconnect();
        return "웹소켓 연결 종료";
    }
}
