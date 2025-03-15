package org.zerock.stockspring.websocket;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@Log4j2
public class KisWebSocketClient extends WebSocketClient {

    private StockDataListener listener;

    public KisWebSocketClient(URI serverUri){
        super(serverUri);
    }
    public void setListener(StockDataListener listener){
        this.listener = listener;
    }
    @Override
    public void onOpen(ServerHandshake handshakedata){
        log.info("웹소켓 연결 성공");
    }
    @Override
    public void onMessage(String message){
        log.info("메세지 수신: {}", message);
        if(message.startsWith("{")){
            log.info("구독 응답:{} ", message);
        }else if (message.contains("|")) {
            processRealtimeData(message);
        }
    }
    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("웹소켓 연결 종료: code={}, reason={}", code, reason);
    }

    @Override
    public void onError(Exception ex) {
        log.error("웹소켓 오류 발생", ex);
    }

    private void processRealtimeData(String message) {
        try {
            // 0|H0STCNT0|001|005930^123456^75000^2^... 형식 처리
            String[] parts = message.split("\\|");

            if (parts.length < 4) return;

            String trId = parts[1];        // TR ID (H0STCNT0)
            int dataCount = Integer.parseInt(parts[2]); // 데이터 건수
            String rawData = parts[3];     // 실제 데이터

            // H0STCNT0 (실시간 주식 체결가) 처리
            if ("H0STCNT0".equals(trId)) {
                String[] records = rawData.split("\\^");

                if (records.length >= 10) {
                    StockPrice stockPrice = new StockPrice();
                    stockPrice.setStockCode(records[0]);          // 종목코드
                    stockPrice.setTime(records[1]);               // 체결시간
                    stockPrice.setPrice(Integer.parseInt(records[2])); // 현재가
                    stockPrice.setSign(records[3]);               // 전일대비부호
                    stockPrice.setChange(Integer.parseInt(records[4])); // 전일대비
                    stockPrice.setChangeRate(Double.parseDouble(records[5])); // 등락률
                    stockPrice.setVolume(Long.parseLong(records[13])); // 누적거래량

                    // 리스너를 통해 이벤트 전달
                    if (listener != null) {
                        listener.onStockPriceReceived(stockPrice);
                    }
                }
            }
        } catch (Exception e) {
            log.error("실시간 데이터 처리 중 오류", e);
        }
    }


}
