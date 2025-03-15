package org.zerock.stockspring.websocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.stockspring.config.ApiConfig;
import org.zerock.stockspring.portfolio.dto.BalanceResponseDTO;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Log4j2
@RequiredArgsConstructor
public class KisWebSocketService implements StockDataListener {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;



    private KisWebSocketClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final WebClient webClient = WebClient.builder().build();

    // 웹소켓 접속키 발급
    public String getApprovalKey() {
        try {
            // 접속키 발급 엔드포인트 URL
            String url = "https://openapivts.koreainvestment.com:29443/oauth2/Approval";

            // 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 요청 본문 구성
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("grant_type", "client_credentials");
            requestBody.put("appkey", apiConfig.getAppKey());
            requestBody.put("secretkey", apiConfig.getAppSecret());

            // 요청 엔티티 생성
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            // API 호출
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, requestEntity, Map.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                Map<String, String> responseBody = responseEntity.getBody();
                String approvalKey = responseBody.get("approval_key");
                log.info("웹소켓 접속키 발급 성공: {}", approvalKey);
                return approvalKey;
            } else {
                log.error("웹소켓 접속키 발급 실패: {}", responseEntity.getBody());
                throw new RuntimeException("웹소켓 접속키 발급 실패");
            }
        } catch (Exception e) {
            log.error("웹소켓 접속키 발급 중 오류 발생", e);
            throw new RuntimeException("웹소켓 접속키 발급 중 오류", e);
        }
    }

    // 웹소켓 연결 및 종목 구독
    public void connectAndSubscribe(String stockCode) {
        try {
            // 접속키 발급
            String approvalKey = getApprovalKey();

            // 웹소켓 서버 URI (모의투자/실전투자)
            String wsUrl = "ws://ops.koreainvestment.com:31000";  // 모의투자


            // 웹소켓 클라이언트 생성 및 연결
            client = new KisWebSocketClient(new URI(wsUrl));
            client.setListener(this);
            client.addHeader("approval_key", approvalKey);
            client.addHeader("custtype", "P");  // P: 개인
            client.connect();

            // 연결 대기
            while (!client.isOpen()) {
                Thread.sleep(100);
            }

            // 종목 구독 요청
            subscribeStock(stockCode);

            log.info("종목({}) 구독 요청 완료", stockCode);
        } catch (Exception e) {
            log.error("웹소켓 연결 오류", e);
        }
    }

    // 종목 구독 요청
    public void subscribeStock(String stockCode) {
        try {
            Map<String, Object> header = new HashMap<>();
            header.put("tr_type", "1");  // 1: 등록
            header.put("tr_id", "H0STCNT0"); // 실시간 주식 체결가
            header.put("tr_key", stockCode); // 종목코드

            Map<String, Object> request = new HashMap<>();
            request.put("header", header);
            request.put("body", new HashMap<>());

            String requestJson = objectMapper.writeValueAsString(request);
            client.send(requestJson);
        } catch (Exception e) {
            log.error("종목 구독 요청 오류", e);
        }
    }

    // 웹소켓 연결 종료
    public void disconnect() {
        if (client != null && client.isOpen()) {
            client.close();
            log.info("웹소켓 연결 종료");
        }
    }

    // 실시간 주가 데이터 수신 처리
    @Override
    public void onStockPriceReceived(StockPrice stockPrice) {
        log.info("실시간 주가 수신: {}", stockPrice);
        // 여기서 데이터를 저장하거나 다른 컴포넌트에 전달
    }
}
