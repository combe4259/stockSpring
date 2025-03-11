package org.zerock.stockspring;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zerock.stockspring.config.ApiConfig;

@SpringBootApplication
@EnableConfigurationProperties(ApiConfig.class)
@EnableScheduling
public class StockSpringApplication {

    private final ApiConfig apiConfig;

    public StockSpringApplication(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(StockSpringApplication.class, args);
    }

}
