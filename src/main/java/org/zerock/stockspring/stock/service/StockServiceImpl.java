package org.zerock.stockspring.stock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.stockspring.config.ApiConfig;
import org.zerock.stockspring.stock.dto.DailyPriceResponseDTO;
import org.zerock.stockspring.stock.dto.QuotationResponseDTO;
import org.zerock.stockspring.token.AccessTokenService;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Log4j2
public class StockServiceImpl implements StockService {


    private final ApiConfig apiConfig;
    private final AccessTokenService accessTokenService;
    private final RestTemplate restTemplate;

    private final String ACCOUNT_NUMBER = "50124248";
    private static final String PRODUCT_CODE = "01";

    private HttpHeaders baseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Bearer " + accessTokenService.getAccessToken());
        log.info("이거 잘 되나요 !!!!!!"+accessTokenService.getAccessToken());
        headers.set("appkey", apiConfig.getAppKey());
        headers.set("appsecret", apiConfig.getAppSecret());
        return headers;
    }

    @Override
    public QuotationResponseDTO getPresentService() {
        log.info("현재가 조회 시작");
        URI uri = UriComponentsBuilder
                .fromUriString(apiConfig.getVdomain() + "/uapi/domestic-stock/v1/quotations/inquire-price")
                .queryParam("FID_COND_MRKT_DIV_CODE", "J")
                .queryParam("FID_INPUT_ISCD", "005930")//추후 동적 변경
                .build()
                .toUri();
        String trId = "FHKST01010100";
        HttpHeaders headers = baseHeaders();
        headers.set("tr_id", trId);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<QuotationResponseDTO> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                requestEntity,
                QuotationResponseDTO.class
        );
        QuotationResponseDTO response = responseEntity.getBody();
        log.info("현재가 조회 가공");
        return response;
    }

    //일별 시세 조회
    @Override
    public DailyPriceResponseDTO getDailyPriceService(){
        log.info("일별 시세 조회 시작");
        URI uri = UriComponentsBuilder
                .fromUriString(apiConfig.getVdomain()+"/uapi/domestic-stock/v1/quotations/inquire-daily-itemchartprice")
                .queryParam("FID_COND_MRKT_DIV_CODE", "J")
                .queryParam("FID_INPUT_ISCD", "005930")
                .queryParam("FID_INPUT_DATE_1", "2022-01-01")
                .queryParam("FID_INPUT_DATE_2", "2022-01-31")
                .queryParam("FID_PERIOD_DIV_CODE", "D")
                .queryParam("FID_ORG_ADJ_PRC", "1")
                .build()
                .toUri();

        String trId = "FHKST03010100";
        HttpHeaders headers = baseHeaders();
        headers.set("tr_id", trId);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<DailyPriceResponseDTO> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                requestEntity,
                DailyPriceResponseDTO.class
        );

        DailyPriceResponseDTO response = responseEntity.getBody();
        log.info("일별 시세 조회 가공");
        return response;
    }


}
