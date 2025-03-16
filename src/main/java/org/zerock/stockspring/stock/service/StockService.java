package org.zerock.stockspring.stock.service;

import org.zerock.stockspring.stock.dto.DailyPriceResponseDTO;
import org.zerock.stockspring.stock.dto.QuotationResponseDTO;

public interface StockService {
    QuotationResponseDTO getPresentService(String stockCode);
    DailyPriceResponseDTO getDailyPriceService(String stockCode, String startDate, String endDate);
}
