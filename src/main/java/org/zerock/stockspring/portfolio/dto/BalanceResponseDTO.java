package org.zerock.stockspring.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceResponseDTO {
    String rt_cd; 	//성공 실패 여부
    String msg_cd;	//응답코드
    String msg1;     //응답메세지
    String ctx_area_fk100;  //	연속조회검색조건100
    String ctx_area_nk100;  //	연속조회키100
    private List<Output1> output1;
    private List<Output2> output2;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Output1{
        String pdno;  //	상품번호
        String prdt_name;  //	상품명
        String trad_dvsn_name;  //	매매구분명
        String bfdy_buy_qty;  //	전일매수수량
        String bfdy_sll_qty;  //	전일매도수량
        String thdt_buyqty;  //	금일매수수량
        String thdt_sll_qty;  //	금일매도수량
        String hldg_qty;  //	보유수량
        String ord_psbl_qty;  //	주문가능수량
        String pchs_avg_pric;  //	매입평균가격
        String pchs_amt;  //	매입금액
        String prpr;  //	현재가
        String evlu_amt;  //	평가금액
        String evlu_pfls_amt;  //	평가손익금액
        String evlu_pfls_rt;  //	평가손익율
        String evlu_erng_rt;  //	평가수익율
        String loan_dt;  //	대출일자
        String oan_amt;  //	대출금액
        String stln_slng_chgs;  //	대주매각대금
        String expd_dt;  //	만기일자
        String fltt_rt;  //	등락율
        String bfdy_cprs_icdc;  //	전일대비증감
        String item_mgna_rt_name;  //	종목증거금율명
        String grta_rt_name;  //	보증금율명
        String sbst_pric;  //	대용가격
        String stck_loan_unpr;  //	주식대출단가
    }
   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   public static class Output2{
        String dnca_tot_amt;  //	예수금총금액
        String nxdy_excc_amt;  //	익일정산금액
        String prvs_rcdl_excc_amt;  //	가수도정산금액
        String cma_evlu_amt;  //	CMA평가금액
        String bfdy_buy_amt;  //	전일매수금액
        String thdt_buy_amt;  //	금일매수금액
        String nxdy_auto_rdpt_amt;  //	익일자동상환금액
        String bfdy_sll_amt;  //	전일매도금액
        String thdt_sll_amt;  //	금일매도금액
        String d2_auto_rdpt_amt;  //	D+2자동상환금액
        String bfdy_tlex_amt;  //	전일제비용금액
        String thdt_tlex_amt;  //	금일제비용금액
        String tot_loan_amt;  //	총대출금액
        String scts_evlu_amt;  //	유가평가금액
        String tot_evlu_amt;  //	총평가금액
        String nass_amt;  //	순자산금액
        String fncg_gld_auto_rdpt_yn;  //	융자금자동상환여부
        String pchs_amt_smtl_amt;  //	매입금액합계금액
        String evlu_amt_smtl_amt;  //	평가금액합계금액
        String evlu_pfls_smtl_amt;  //	평가손익합계금액
        String tot_stln_slng_chgs;  //	총대주매각대금
        String bfdy_tot_asst_evlu_amt;  //	전일총자산평가금액
        String asst_icdc_amt;  //	자산증감액
   }

}

