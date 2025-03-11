package org.zerock.stockspring.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
public class TokenRequestDTO {
    private String grant_type;
    private String appKey;
    private String appSecret;


}
