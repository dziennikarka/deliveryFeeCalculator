package com.example.deliveryFeeCalculatorBackend.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void testCartEqualsHundred() {
        int actual = calculator.calculateDeliveryFee(10000, 500, 4, "2021-10-12T13:00:00Z");
        assertEquals(0, actual, "Delivery of cart with total of 100€ is free");
    }

    @Test
    public void testCartMoreThanHundred() {
        int actual = calculator.calculateDeliveryFee(10001, 500, 4, "2021-10-12T13:00:00Z");
        assertEquals(0, actual, "Delivery of cart with total of more than 100 € is free");
    }

    @Test
    public void testCartSurchargeCalculation() {
        int actual = calculator.calculateDeliveryFee(890, 500, 4, "2021-10-12T13:00:00Z");
        assertEquals(310, actual, "If the cart total is less than 10, calculate surcharge, which is 10 - cart value");
    }

    @Test
    public void testFirstKilometerFee() {
        int actual = calculator.calculateDistanceFee(1000);
        assertEquals(200, actual, "Fee for the first kilometer (1000 meters) is 2 €");
    }

    @Test
    public void testAdditionalKilometersFee() {
        int actual = calculator.calculateDistanceFee(1499);
        assertEquals(300, actual, "Fee for extra distance, that is less than 500 meters");
    }

    @Test
    public void testTwoAdditionalKilometersFee() {
        int actual = calculator.calculateDistanceFee(1500);
        assertEquals(300, actual, "Fee for extra distance, that equals to 500 meters");
    }

    @Test
    public void testThreeAdditionalKilometersFee() {
        int actual = calculator.calculateDistanceFee(1501);
        assertEquals(400, actual, "Fee for extra distance, that is more than 500 meters");
    }

    @Test
    public void testSurchargePerItem() {
        int actual = calculator.calculateSurchargePerItem(4);
        assertEquals(0, actual, "If there are less than 5 items in the cart, no surcharge");
    }

    @Test
    public void testTwoSurchargePerItem() {
        int actual = calculator.calculateSurchargePerItem(5);
        assertEquals(50, actual, "If there are 5 items or more in the cart, extra 50 cents are added for each item");
    }

    @Test
    public void testThreeSurchargePerItem() {
        int actual = calculator.calculateSurchargePerItem(10);
        assertEquals(300, actual, "If there are 5 items or more in the cart, extra 50 cents are added for each item");
    }

    @Test
    public void testDeliveryFeeCalculation() {
        int actual = calculator.calculateDeliveryFee(790, 2235, 4, "2021-10-12T13:00:00Z");
        assertEquals(710, actual, "Calculation of delivery fee");
    }

    @Test
    public void testCheckMaximumFee(){
        int actual = calculator.calculateDeliveryFee(9500, 5000, 25, "2021-10-12T13:00:00Z");
        assertEquals(1500, actual, "Delivery fee can never exceed 15 €");
    }

    @Test
    public void testCheckFridays(){
        Boolean actual = calculator.checkIfRushHour("2022-01-28T18:00:00Z");
        assertEquals(true, actual, "Checking whether delivery happens on Friday rush hour");
    }

    @Test
    public void testCheckDeliveryFriday(){
        int actual = calculator.calculateDeliveryFee(790, 2235, 4, "2022-01-28T18:00:00Z");
        assertEquals(781, actual, "Calculation of delivery fee");
    }
}
