package org.zerock.stockspring.websocket;

import lombok.Data;

@Data
public class StockPrice{
    private String stockCode;
    private String time;
    private int price;
    private String sign;
    private int change;
    private double changeRate;
    private long volume;
}
