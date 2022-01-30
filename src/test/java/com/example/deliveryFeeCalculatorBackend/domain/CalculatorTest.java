package com.example.deliveryFeeCalculatorBackend.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    @ParameterizedTest
    @CsvSource({"790,2235, 4, 2021-10-12T13:00:00Z, 710, Calculation of delivery fee",
            "9500, 5000, 25, 2021-10-12T13:00:00Z, 1500, Delivery fee can never exceed 15 €",
            "790, 2235, 4, 2022-01-28T18:00:00Z, 781, Calculation of delivery fee on Friday",
            "1000, 1000, 10, 2021-10-12T13:00:00Z, 500, If there are 5 items or more in the cart extra 50 cents are added for each item",
            "1000, 1000, 5, 2021-10-12T13:00:00Z, 250, If there are 5 items or more in the cart extra 50 cents are added for each item",
            "1000, 1000, 4, 2021-10-12T13:00:00Z, 200, If there are less than 5 items in the cart no surcharge",
            "1000, 1501, 4, 2021-10-12T13:00:00Z, 400, Fee for extra distance that is more than 500 meters",
            "1000, 1500, 4, 2021-10-12T13:00:00Z, 300, Fee for extra distance that equals to 500 meters",
            "1000, 1499, 4, 2021-10-12T13:00:00Z, 300, Fee for extra distance that is less than 500 meters",
            "1000, 1000, 3, 2021-10-12T13:00:00Z, 200, Fee for the first kilometer (1000 meters) is 2 € ",
            "890, 500, 4, 2021-10-12T13:00:00Z, 310, If the cart total is less than 10 calculate surcharge which is 10 - cart value",
            "10001, 500, 4, 2021-10-12T13:00:00Z, 0, Delivery of cart with total of more than 100 € is free",
            "10000, 500, 4, 2021-10-12T13:00:00Z, 0, Delivery of cart with total of 100€ is free"
    })
    public void testMassDeliveryFeeCalculation(int cartTotal, int deliveryDistance, int itemsNumber, String time, int expected, String message) {
        int actual = calculator.calculateDeliveryFee(cartTotal, deliveryDistance, itemsNumber, time);
        assertEquals(expected, actual, message);
    }
}
