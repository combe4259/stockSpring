package org.zerock.stockspring.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class WebSocketConfig {
    @Bean
    public URI webSocketUri() {
        return URI.create("ws://ops.koreainvestment.com:31000");
    }

    @Bean
    public KisWebSocketClient kisWebSocketClient(URI webSocketUri) {
        return new KisWebSocketClient(webSocketUri);
    }
}
