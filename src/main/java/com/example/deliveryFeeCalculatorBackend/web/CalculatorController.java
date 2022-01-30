package com.example.deliveryFeeCalculatorBackend.web;


import com.example.deliveryFeeCalculatorBackend.domain.Calculator;
import com.example.deliveryFeeCalculatorBackend.dto.Fee;
import com.example.deliveryFeeCalculatorBackend.dto.FeeRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Represents controller that accepts request payload as JSON, calculates delivery fee and returns response payload as JSON
 */
@RestController
public class CalculatorController {
    /**
     * Calculates delivery fee based on the data in the fee request
     *
     * @param feerequest data object that consist of value of shopping cart, delivery distance, number of items in the cart and order time
     * @return data object that consist of delivery fee calculated in cents
     */
    @PostMapping("/calculatefee")
    public Fee calculateDeliveryFee(@RequestBody FeeRequest feerequest) {
        Calculator calculator = new Calculator();
        int fee = calculator.calculateDeliveryFee(feerequest.getCartValue(), feerequest.getDeliveryDistance(),
                feerequest.getNumberOfItems(), feerequest.getTime());
        return new Fee(fee);
    }
}
