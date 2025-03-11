package org.zerock.stockspring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "kis-api")
public class ApiConfig {

    private String appKey;
    private String appSecret;
    private final String vdomain = "https://openapivts.koreainvestment.com:29443";

//    private final String ACCOUNT_NUMBER = "50124248";


    public void printProperties(){
        System.out.println("Appkey:"+ appKey);
        System.out.println("AppSecert:"+appSecret);
    }
}
