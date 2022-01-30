package com.example.deliveryFeeCalculatorBackend;

import com.example.deliveryFeeCalculatorBackend.dto.FeeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebLayerCalculatorControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void calculatefee() throws Exception {
        FeeRequest request = new FeeRequest(790, 2235, 4, "2021-10-12T13:00:00Z");

       String actualFee = restTemplate.postForObject("http://localhost:" + port + "/calculatefee", request, String.class);
        assertEquals("{\"delivery_fee\":710}", actualFee);
    }
}
