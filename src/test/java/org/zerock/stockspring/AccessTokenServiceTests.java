package org.zerock.stockspring;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.zerock.stockspring.token.AccessTokenService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@SpringBootTest
public class AccessTokenServiceTests {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGetAccessToken(){
        accessTokenService.generateAccessToken();
        String token = accessTokenService.getAccessToken();
        assertNotNull(token);
        log.info("Access Token: "+token);

    }
}
