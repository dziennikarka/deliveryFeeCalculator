package com.example.deliveryFeeCalculatorBackend;

import com.example.deliveryFeeCalculatorBackend.web.CalculatorController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DeliveryFeeCalculatorBackendApplicationTests {
	@Autowired
	private CalculatorController calculatorController;

	@Test
	void contextLoads() throws Exception {
		assertThat(calculatorController).isNotNull();
	}

}
