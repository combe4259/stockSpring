package org.zerock.stockspring.websocket;

public interface StockDataListener {
    void onStockPriceReceived(StockPrice stockPrice);
}
