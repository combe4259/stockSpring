package org.zerock.stockspring.portfolio.service;

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
import org.zerock.stockspring.portfolio.dto.BalanceResponseDTO;
import org.zerock.stockspring.token.AccessTokenService;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Log4j2
public class PortfolioServiceImpl implements PortfolioService{

    private final ApiConfig apiConfig;
    private final AccessTokenService accessTokenService;
    //추후 동적입력으로 변경
    private final String ACCOUNT_NUMBER = "50124248";
    private static final String PRODUCT_CODE = "01";
    //임시로 동기로 불러옴
    private final RestTemplate restTemplate;

    @Override
    public BalanceResponseDTO getBalanceService(){
        log.info("주식 잔고 조회 시작");
        URI uri = UriComponentsBuilder
                .fromUriString(apiConfig.getVdomain() + "/uapi/domestic-stock/v1/trading/inquire-balance")
                .queryParam("CANO", ACCOUNT_NUMBER)
                .queryParam("ACNT_PRDT_CD", PRODUCT_CODE) //추후 동적 변경
                .queryParam("AFHR_FLPR_YN", "N")
                .queryParam("OFL_YN", "")
                .queryParam("INQR_DVSN", "02")  // 종목별 조회
                .queryParam("UNPR_DVSN", "01")
                .queryParam("FUND_STTL_ICLD_YN", "N")
                .queryParam("FNCG_AMT_AUTO_RDPT_YN", "N")
                .queryParam("PRCS_DVSN", "00")  // 전일매매포함
                .queryParam("CTX_AREA_FK100", "") //연속조회 OUTPUT에서 꺼내와야함
                .queryParam("CTX_AREA_NK100", "") //일단 공란 처리(최초 조회)
                .build()
                .toUri();

        String trId = "VTTC8434R";//[실전투자] TTTC8434R
        HttpHeaders headers = baseHeaders();
        headers.set("tr_id",trId);
//        if (ctxAreaFk100 != null && !ctxAreaFk100.isEmpty()) {
//            headers.set("tr_cont", "N");  // 연속 조회
//        }
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<BalanceResponseDTO> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, requestEntity, BalanceResponseDTO.class);

        BalanceResponseDTO response = responseEntity.getBody();
        log.info("잔고 정보 가공: "+ response);
        return response;


    }



    private HttpHeaders baseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Bearer " + accessTokenService.getAccessToken());
        log.info("이거 잘 되나요 !!!!!!"+accessTokenService.getAccessToken());
        headers.set("appkey", apiConfig.getAppKey());
        headers.set("appsecret", apiConfig.getAppSecret());
        return headers;
    }







}
