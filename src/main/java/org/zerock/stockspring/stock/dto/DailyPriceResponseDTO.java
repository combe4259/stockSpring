package org.zerock.stockspring.stock.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyPriceResponseDTO {
    private String rt_cd; // 성공 실패 여부
    private String msg_cd; // 응답코드
    private String msg1; // 응답메세지
    private Output output1; // 응답상세
    private List<Output2> output2;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Output{
        private String prdy_vrss; // 전일 대비
        private String prdy_vrss_sign; // 전일 대비 부호
        private String prdy_ctrt; // 전일 대비율
        private String stck_prdy_clpr; // 주식 전일 종가
        private String acml_vol; // 누적 거래량
        private String acml_tr_pbmn; // 누적 거래 대금
        private String hts_kor_isnm; // HTS 한글 종목명
        private String stck_prpr; // 주식 현재가
        private String stck_shrn_iscd; // 주식 단축 종목코드
        private String prdy_vol; // 전일 거래량
        private String stck_mxpr; // 주식 상한가
        private String stck_llam; // 주식 하한가
        private String stck_oprc; // 주식 시가2
        private String stck_hgpr; // 주식 최고가
        private String stck_lwpr; // 주식 최저가
        private String stck_prdy_oprc; // 주식 전일 시가
        private String stck_prdy_hgpr; // 주식 전일 최고가
        private String stck_prdy_lwpr; // 주식 전일 최저가
        private String askp; // 매도호가
        private String bidp; // 매수호가
        private String prdy_vrss_vol; // 전일 대비 거래량
        private String vol_tnrt; // 거래량 회전율
        private String stck_fcam; // 주식 액면가
        private String lstn_stcn; // 상장 주수
        private String cpfn; // 자본금
        private String hts_avls; // HTS 시가총액
        private String per; // PER
        private String eps; // EPS
        private String pbr; // PBR
        private String itewhol_loan_rmnd_ratem; // 전체 융자 잔고 비율
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Output2{
                private String stck_bsop_date; // 주식 영업 일자
                private String stck_clpr; // 주식 종가
                private String stck_oprc; // 주식 시가
                private String stck_hgpr; // 주식 최고가
                private String stck_lwpr; // 주식 최저가
                private String acml_vol; // 누적 거래량
                private String acml_tr_pbmn; // 누적 거래 대금
                private String flng_cls_code; // 플링 클래스 코드
                private String prtt_rate; // 비율
                private String mod_yn; // 수정 여부
                private String prdy_vrss_sign; // 전일 대비 부호
                private String prdy_vrss; // 전일 대비
                private String revl_issu_reas; // 발행 이유"stck_bsop_date": "20220809",
    }


}
