package org.zerock.stockspring.token;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.zerock.stockspring.config.ApiConfig;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class AccessTokenService {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;

    public String accessToken;
    private boolean initialized = false;


//    @PostConstruct
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")   // 매일 자정에 증권사 API 접근 토큰 자동 발급
    public void generateAccessToken() {

        log.info("API 토근 발급 시작");

        String url = "https://openapivts.koreainvestment.com:29443/oauth2/tokenP";

        Map<String, String> requestBody = new LinkedHashMap<>();
        requestBody.put("grant_type", "client_credentials");
        requestBody.put("appkey", apiConfig.getAppKey());
        requestBody.put("appsecret", apiConfig.getAppSecret());

        //JSON type 지정해주는 header추가
        HttpEntity<Object> requestMessage = new HttpEntity<>(requestBody);

        ResponseEntity<TokenResponseDTO> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestMessage,
                        TokenResponseDTO.class
                );
        TokenResponseDTO tokenResponse = response.getBody();
        if(tokenResponse!=null){
            this.accessToken=tokenResponse.getAccessToken();
//            log.info(accessToken);
            log.info("발급 성공");
        }else{
            log.info("발급 실패");
        }
    }

    public String getAccessToken(){
        if(accessToken == null && !initialized){
            try {
                generateAccessToken();;
                initialized = true;
            }catch (Exception e){
                log.error("토큰 발급 실패");
                return null;
            }
        }
        log.info("발급된 access token 반환");
        return this.accessToken;
    }
}
